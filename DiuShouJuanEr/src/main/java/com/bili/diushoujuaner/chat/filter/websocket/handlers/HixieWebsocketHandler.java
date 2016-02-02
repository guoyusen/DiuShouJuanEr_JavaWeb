package com.bili.diushoujuaner.chat.filter.websocket.handlers;


import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.bili.diushoujuaner.chat.filter.websocket.dto.WebsocketOpcode;
import com.bili.diushoujuaner.chat.filter.websocket.dto.WebsocketOpeningHandshakeHeader;
import com.bili.diushoujuaner.chat.filter.websocket.exceptions.InvalidWebsocketOpeningHandler;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HixieWebsocketHandler extends AbstractWebsocketHandler {
    public HixieWebsocketHandler(WebsocketOpeningHandshakeHeader openingHandshakeHeader,
                                 WebsocketEventCallback callback) {
        super(openingHandshakeHeader, callback);
    }

    @Override
    public boolean doHandleMessage(ByteBuffer buffer) {
        byte type = buffer.get();

        if ((type & 0x80) == 0x00) {//表示这不是最后一个片段
            if (type != 0) {//如果这时type反而不为0，那么一定是2,3,4位出现了其他
                callback.close(0, "");
            } else {//0000 0000 非最后分，无掩码，有后续帧
                ByteBuffer raw_data_buffer = ByteBuffer.allocate(buffer.remaining());
                byte b;

                while ((b = buffer.get()) != (byte) 0xFF) {
                    raw_data_buffer.put(b);
                }
                raw_data_buffer.flip();
                int length = raw_data_buffer.limit();
                byte[] raw_data = new byte[length];
                raw_data_buffer.get(raw_data, 0, length);
                messageCallback(raw_data, false);
            }
        } else {//表示这是最后一个片段
            if (type != 0xFF) {
                callback.close(0, "");
            } else {
                int length = 0;
                byte b;
                do {
                    b = buffer.get();
                    int b_v = b & 0x7F;
                    length = (length * 128) + b_v;
                } while ((b & 0x80) == 0x80);
                byte[] skip = new byte[length];
                buffer.get(skip, 0, length);

                if (length == 0) {
                    callback.close(0, "");
                }
            }
        }
        return true;
    }

    @Override
    protected void pong(byte[] payload) {
        // hixie doesnt support ping pong, do nothing
    }

    @Override
    protected void ping(byte[] payload) {
        // hixie doesnt support ping pong, do nothing
    }

    @Override
    public byte[] getOpeningHandshakeResponse() throws Exception {
        String subProtocol = openingHandshakeHeader.getSubProtocol();
        //remove hardcoded wss as ws fails on safari otherwise
        StringBuilder sb = new StringBuilder("HTTP/1.1 101 Switching Protocols\r\n")
                .append("Upgrade: WebSocket\r\n")
                .append("Connection: Upgrade\r\n").append("Sec-WebSocket-Location: wss://").append(openingHandshakeHeader.getHost()).append(openingHandshakeHeader.getResource()).append("\r\n").append("Sec-WebSocket-Origin: ").append(openingHandshakeHeader.getOrigin()).append("\r\n");
        if (null != subProtocol)
            sb.append("Sec-WebSocket-Protocol: ").append(subProtocol).append("\r\n");
        sb.append("\r\n");

        ByteBuffer bb = ByteBuffer.allocate(sb.toString().length() + 16);
        bb.put(sb.toString().getBytes(UTF_8));
        bb.put(getHixieWebSocketKeyChallengeResponse(
                openingHandshakeHeader.getKey1(), openingHandshakeHeader.getKey2(), openingHandshakeHeader.getKey3()));
        return bb.array();
    }

    @Override
    public ByteBuffer encodeMessage(WebsocketOpcode websocketOpcode, byte[] msg, boolean isBinary) {
        if (websocketOpcode == WebsocketOpcode.TEXT && !isBinary) {
            ByteBuffer out = ByteBuffer.allocate(msg.length + 2);

            out.put((byte) 0x00);
            out.put(msg);
            out.put((byte) 0xFF);
            out.flip();
            return out;
        }

        return ByteBuffer.allocate(0);
    }

    private static byte[] getHixieWebSocketKeyChallengeResponse(String key1, String key2, byte[] key3)
            throws InvalidWebsocketOpeningHandler, NoSuchAlgorithmException {
        MessageDigest cript = MessageDigest.getInstance("MD5");
        cript.reset();
        cript.update(getDigitsFromHixieSecKey(key1));
        cript.update(getDigitsFromHixieSecKey(key2));
        cript.update(key3);
        return cript.digest();
    }

    public static byte[] getDigitsFromHixieSecKey(String key) throws InvalidWebsocketOpeningHandler {
        long digits = 0;
        int spaces = 0;

        for (char ch : key.toCharArray()) {
            if (Character.isDigit(ch))
                digits = (digits * 10) + Character.getNumericValue(ch);
            else if (ch == ' ')
                spaces++;
        }

        if (spaces == 0 || digits % spaces != 0)
            throw new InvalidWebsocketOpeningHandler("Invalid sec key : " + key);

        ByteBuffer digitsinBigEndian = ByteBuffer.allocate(4);
        digitsinBigEndian.putInt((int) (digits / spaces));
        return digitsinBigEndian.array();
    }
}
