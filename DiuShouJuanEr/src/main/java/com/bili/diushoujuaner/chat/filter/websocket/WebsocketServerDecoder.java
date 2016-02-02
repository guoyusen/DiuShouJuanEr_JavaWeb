package com.bili.diushoujuaner.chat.filter.websocket;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bili.diushoujuaner.chat.filter.websocket.dto.DecoderState;
import com.bili.diushoujuaner.chat.filter.websocket.dto.WebsocketMessage;
import com.bili.diushoujuaner.chat.filter.websocket.dto.WebsocketOpcode;
import com.bili.diushoujuaner.chat.filter.websocket.dto.WebsocketOpeningHandshakeHeader;
import com.bili.diushoujuaner.chat.filter.websocket.exceptions.IncompleteHandshakePacket;
import com.bili.diushoujuaner.chat.filter.websocket.exceptions.NotWebsocketHandshakeRequest;
import com.bili.diushoujuaner.chat.filter.websocket.exceptions.UnsupportedWebsocketProtocol;
import com.bili.diushoujuaner.chat.filter.websocket.handlers.AbstractWebsocketHandler;
import com.bili.diushoujuaner.chat.filter.websocket.handlers.WebsocketEventCallback;

import java.util.Arrays;


public class WebsocketServerDecoder extends CumulativeProtocolDecoder {

    private static final Logger logger = LoggerFactory.getLogger(WebsocketServerDecoder.class);

    @Override
    protected boolean doDecode(final IoSession session, final IoBuffer in, final ProtocolDecoderOutput out) throws Exception {
        DecoderState state = (DecoderState) session.getAttribute(WebsocketServerCodecFactory.WEBSOCKET_STATE_ATT);
        if (null == state) {
            session.setAttribute(WebsocketServerCodecFactory.WEBSOCKET_STATE_ATT, state = DecoderState.HANDSHAKE);
        }
        int start = in.position();
        switch (state) {
            case HANDSHAKE:
                try {
                    WebsocketOpeningHandshakeHeader openingHandshakeHeader = WebsocketOpeningHandshakeHeader.getWebsocketOpeningHandshakeHeader(in.buf());
                    AbstractWebsocketHandler websocketHandler = AbstractWebsocketHandler.getHandler(openingHandshakeHeader,
                            new WebsocketEventCallback() {

                                @Override
                                public void close(int reasonCode, String reasonDescription) {
                                    logger.debug("Got <close> reasonCode :: {} , Desciption :: {}", reasonCode, reasonDescription);
                                    session.write(new WebsocketMessage(WebsocketOpcode.CLOSE, false, new byte[0]));
                                    session.close(true);
                                }

                                @Override
                                public void ping(byte[] msg) {
                                    logger.debug("Got <ping> msg :: " + new String(msg));
                                    session.write(new WebsocketMessage(WebsocketOpcode.PONG, false, msg));
                                }

                                @Override
                                public void pong(byte[] msg) {
                                    logger.debug("Got <pong> msg :: " + new String(msg));
                                    out.write(new WebsocketMessage(WebsocketOpcode.PONG, false, msg));
                                }

                                @Override
                                public void message(byte[] msg) {
                                    logger.debug("Got message :: {}", Arrays.toString(msg));
                                    out.write(msg);
                                }

                                @Override
                                public void message(String msg) {
                                    logger.debug("Got message :: {}", msg);
                                    out.write(msg);
                                }

                            });
                    session.setAttribute(WebsocketServerCodecFactory.WEBSOCKET_HANDLER_ATT, websocketHandler);
                    session.write(IoBuffer.wrap(websocketHandler.getOpeningHandshakeResponse()));
                    session.setAttribute(WebsocketServerCodecFactory.WEBSOCKET_STATE_ATT, DecoderState.ACCEPTING_MESSAGES);
                } catch (IncompleteHandshakePacket e) {
                    in.position(start);
                    return false;
                } catch (NotWebsocketHandshakeRequest | UnsupportedWebsocketProtocol e) {
                    abortWebsocketRequest(session, 400, e);
                }
                break;
            case ACCEPTING_MESSAGES:
                AbstractWebsocketHandler websocketHandler = (AbstractWebsocketHandler) session.getAttribute(WebsocketServerCodecFactory.WEBSOCKET_HANDLER_ATT);
                if (!websocketHandler.handleMessage(in.buf())) {
                    in.position(start);
                    return false;
                }
                break;
            case PROXY:
                out.write(in);
                break;
        }
        return true;
    }

    private void abortWebsocketRequest(IoSession session, int code, Throwable t) {
        logger.error("Aborting websocket request with code = {} description = ", code, t);
        session.write(new StringBuilder("HTTP/1.1 ").append(code).append(' ')
                .append(t.getMessage()).append("\r\nContent-type: text/html").toString());
        session.close(false);
    }


}
