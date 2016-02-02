package com.bili.diushoujuaner.chat.filter.websocket;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.bili.diushoujuaner.chat.filter.websocket.dto.DecoderState;
import com.bili.diushoujuaner.chat.filter.websocket.dto.WebsocketMessage;
import com.bili.diushoujuaner.chat.filter.websocket.dto.WebsocketOpcode;
import com.bili.diushoujuaner.chat.filter.websocket.handlers.AbstractWebsocketHandler;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;


public class WebsocketServerEncoder implements ProtocolEncoder {

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws IllegalArgumentException, UnsupportedEncodingException {
        byte[] buffer;
        boolean isBinary = false;
        WebsocketOpcode opcode = WebsocketOpcode.TEXT;
        if (message instanceof String) {
        	// 必须指定该编码格式，否则web客户端无法得到正确的数据
            buffer = ((String) message).getBytes("UTF-8");
        } else if (message instanceof byte[]) {
            buffer = (byte[]) message;
            opcode = WebsocketOpcode.BINARY;
            isBinary = true;
        } else if (message instanceof IoBuffer) {
            buffer = ((IoBuffer) message).array();
            opcode = WebsocketOpcode.BINARY;
            isBinary = true;
        } else if (message instanceof WebsocketMessage) {
            WebsocketMessage websocketMessage = (WebsocketMessage) message;
            opcode = websocketMessage.getOpcode();
            buffer = websocketMessage.getPayload();
            isBinary = websocketMessage.isBinary();
        } else {
            throw new IllegalArgumentException("Only accepts byte[], String and IoBuffer");
        }
        DecoderState state = (DecoderState) session.getAttribute(WebsocketServerCodecFactory.WEBSOCKET_STATE_ATT);
        if (null == state) {
            session.setAttribute(WebsocketServerCodecFactory.WEBSOCKET_STATE_ATT, state = DecoderState.PROXY);
        }
        switch (state) {
            case HANDSHAKE:
                out.write(IoBuffer.wrap(buffer));
                break;
            case ACCEPTING_MESSAGES:
                AbstractWebsocketHandler websocketHandler = (AbstractWebsocketHandler) session.getAttribute(WebsocketServerCodecFactory.WEBSOCKET_HANDLER_ATT);
                ByteBuffer byteBuffer = websocketHandler.encodeMessage(opcode, buffer, isBinary);
                if(byteBuffer != null && byteBuffer.limit() != 0)
                    out.write(IoBuffer.wrap(byteBuffer));
                break;
            case PROXY:
                out.write(message);
                break;
        }

    }

    @Override
    public void dispose(IoSession session) throws Exception {

    }
}
