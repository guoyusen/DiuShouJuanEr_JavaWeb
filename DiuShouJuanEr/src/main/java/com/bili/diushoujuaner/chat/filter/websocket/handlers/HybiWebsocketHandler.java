package com.bili.diushoujuaner.chat.filter.websocket.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bili.diushoujuaner.chat.filter.websocket.dto.WebsocketOpcode;
import com.bili.diushoujuaner.chat.filter.websocket.dto.WebsocketOpeningHandshakeHeader;
import com.bili.diushoujuaner.chat.filter.websocket.exceptions.PayloadLengthExceedsAllowedLimit;

import javax.xml.bind.DatatypeConverter;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class HybiWebsocketHandler extends AbstractWebsocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(HybiWebsocketHandler.class);

    private WebsocketOpcode lastOpcode = null;
    private ByteBuffer fragmentedBuffer = null;

    public HybiWebsocketHandler(WebsocketOpeningHandshakeHeader openingHandshakeHeader,
                                WebsocketEventCallback callback) {
        super(openingHandshakeHeader, callback);
    }

    public void handleFragmentedMessage(byte[] msg, boolean isBinary, boolean fin) {
        if (!fin) {
            if (null == fragmentedBuffer) {
                fragmentedBuffer = ByteBuffer.wrap(msg);
                fragmentedBuffer.position(fragmentedBuffer.limit());
            } else {
                fillFragmentedBuffer(msg);
            }
        } else {
            if (null == fragmentedBuffer) {
                messageCallback(msg, isBinary);
            } else {
                fillFragmentedBuffer(msg);
                fragmentedBuffer.flip();
                byte[] tmp = new byte[fragmentedBuffer.limit()];
                fragmentedBuffer.get(tmp);
                fragmentedBuffer = null;
                messageCallback(tmp, isBinary);
            }
        }
    }

    private void fillFragmentedBuffer(byte[] msg) {
        if (fragmentedBuffer.remaining() < msg.length) {
            int minNewCapicity = fragmentedBuffer.capacity() + msg.length;
            int doubleCapicity = fragmentedBuffer.capacity() * 2;
            int newCapicity = Math.max(minNewCapicity, doubleCapicity);
            ByteBuffer tmpBuffer = ByteBuffer.allocate(newCapicity);
            fragmentedBuffer.flip();
            tmpBuffer.put(fragmentedBuffer);
            fragmentedBuffer = tmpBuffer;
        }
        fragmentedBuffer.put(msg);
    }


    @Override
    public boolean doHandleMessage(ByteBuffer buffer) throws PayloadLengthExceedsAllowedLimit {
        byte b = buffer.get();
        boolean fin = ((b & 0x80) == 0x80);

        WebsocketOpcode opcode = WebsocketOpcode.get((byte) (b & 0x0F));

        if ((b & 0x70) != 0x0) {
            callback.close(1002, "RSV bits must be 0");
            return true;
        }

        if (opcode.isControlOpcode()) {
            if (!fin) {
                callback.close(1002, "control message MUST NOT be fragmented");
                return true;
            }
        } else {
            if (WebsocketOpcode.CONTINUATION == opcode) {
                logger.debug("got continuation, lastOpcode : {}", lastOpcode);
                if (lastOpcode == null) {
                    callback.close(1002, "first fragment with opcode continuation");
                    return true;
                }

                opcode = lastOpcode;
            } else {
                if (lastOpcode != null) {
                    callback.close(1002, "all data frames after the initial data frame must have continuation opcode");
                    return true;
                }
            }
        }

        b = buffer.get();
        boolean masked = ((b & 0x80) == 0x80);
        int payloadLength = b & 0x7F;

        if (payloadLength >= 126 && opcode.isControlOpcode()) {
            callback.close(1002, "control frames are only allowed to have payload up to and including 125 octets");
            return true;
        }


        if (payloadLength == 126) {
            payloadLength = buffer.getShort() & 0xFFFF;
        } else if (payloadLength == 127) {
            long payloadLengthLong = buffer.getLong();
            if (payloadLengthLong > Integer.MAX_VALUE) {
                callback.close(1009, "Maximum length supported for a message is " + Integer.MAX_VALUE);
                return true;
            }
            payloadLength = (int) payloadLengthLong;
        }

        byte mask[] = new byte[4];
        if (masked) {
            for (int i = 0; i < 4; i++) {
                mask[i] = buffer.get();
            }
        }

        if (buffer.limit() - buffer.position() < payloadLength) {
            return false;
        }

        byte[] payload = new byte[payloadLength];
        if (masked) {
            for (int i = 0; i < payloadLength; i++) {
                byte maskedByte = buffer.get();
                payload[i] = (byte) (maskedByte ^ mask[i % 4]);
            }
        } else {
            buffer.get(payload, 0, payloadLength);
        }

        switch (opcode) {
            case TEXT:
                handleFragmentedMessage(payload, false, fin);
                break;
            case BINARY:
                handleFragmentedMessage(payload, true, fin);
                break;
            case RESERVED:
            case CLOSE:
                int reasonCode = 0;
                String reasonDescription = "";

                if (payloadLength > 1)
                    reasonCode = payload[1] + (payload[0] << 8);
                if (payloadLength > 2)
                    reasonDescription = new String(payload, 2, payloadLength - 2);
                callback.close(reasonCode, reasonDescription);
                break;
            case PING:
                callback.ping(payload);
                break;
            case PONG:
                callback.pong(payload);
                break;
		default:
			break;
        }

        if (!opcode.isControlOpcode()) {
            if (fin) {
                lastOpcode = null;
            } else {
                lastOpcode = opcode;
            }
        }

        return true;
    }

    @Override
    protected void pong(byte[] payload) {
        encodeMessage(WebsocketOpcode.PONG, payload, false);
    }

    @Override
    protected void ping(byte[] payload) {
        encodeMessage(WebsocketOpcode.PING, payload, false);
    }

    @Override
    public byte[] getOpeningHandshakeResponse() throws Exception {
        return new StringBuilder("HTTP/1.1 101 Web Socket Protocol Handshake\r\n")
                .append("Upgrade: websocket\r\n")
                .append("Connection: Upgrade\r\n")
                .append("Sec-WebSocket-Accept: ").append(getHybiWebSocketKeyChallengeResponse(openingHandshakeHeader.getKey())).append("\r\n")
                .append("\r\n")
                .toString().getBytes(UTF_8);
    }

    @Override
    public ByteBuffer encodeMessage(WebsocketOpcode opcode, byte[] msg, boolean isBinary) {
        ByteBuffer out;
        byte firstByte = (byte) (opcode.opcode() | 0x80);

        if (msg.length <= 125) {
            out = ByteBuffer.allocate(msg.length + 2);
            out.put(firstByte);
            byte capacity = (byte) (msg.length);
            out.put(capacity);
        } else if (msg.length <= 0xFFFF) {
            out = ByteBuffer.allocate(msg.length + 4);
            out.put(firstByte);
            out.put((byte) 126);
            out.putShort((short) msg.length);
        } else {
            out = ByteBuffer.allocate(msg.length + 10);
            out.put(firstByte);
            out.put((byte) 127);
            out.putLong(msg.length);
        }
        out.put(msg);
        out.flip();
        return out;
    }

    private static String getHybiWebSocketKeyChallengeResponse(String challenge)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        challenge += "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
        MessageDigest cript = MessageDigest.getInstance("SHA-1");
        cript.reset();
        cript.update(challenge.getBytes(UTF_8));
        byte[] hashedVal = cript.digest();
        return DatatypeConverter.printBase64Binary(hashedVal);
    }

}




