package com.bili.diushoujuaner.chat.filter.websocket.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bili.diushoujuaner.chat.filter.websocket.dto.WebsocketOpcode;
import com.bili.diushoujuaner.chat.filter.websocket.dto.WebsocketOpeningHandshakeHeader;
import com.bili.diushoujuaner.chat.filter.websocket.exceptions.PayloadLengthExceedsAllowedLimit;
import com.bili.diushoujuaner.chat.filter.websocket.util.WSUtil;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;

public abstract class AbstractWebsocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(HybiWebsocketHandler.class);

    WebsocketOpeningHandshakeHeader openingHandshakeHeader;
    protected WebsocketEventCallback callback;

    AbstractWebsocketHandler(WebsocketOpeningHandshakeHeader openingHandshakeHeader,
                             WebsocketEventCallback callback) {
        this.openingHandshakeHeader = openingHandshakeHeader;
        this.callback = callback;
    }

    public boolean handleMessage(ByteBuffer buffer) throws PayloadLengthExceedsAllowedLimit {
        try {
            return doHandleMessage(buffer);
        } catch (BufferUnderflowException e) {
            return false;
        }
    }

    protected void messageCallback(byte[] msg, boolean isBinary) {
        if (isBinary) {
            callback.message(msg);
        } else {
            try {
                if (!WSUtil.validateUTF8(msg))
                    throw new CharacterCodingException();
                String message = new String(msg,"UTF-8");
                callback.message(message);
            } catch (Exception e) {
                logger.warn("Not a valid utf-8 string, closing websocket", e);
                callback.close(1007, "Not a valid utf-8 string");
            }
        }
    }

    protected WebsocketOpeningHandshakeHeader getOpeningHandshakeHeader() {
        return openingHandshakeHeader;
    }

    protected abstract boolean doHandleMessage(ByteBuffer buffer) throws PayloadLengthExceedsAllowedLimit;

    protected abstract void pong(byte[] payload);

    protected abstract void ping(byte[] payload);

    public abstract byte[] getOpeningHandshakeResponse() throws Exception;

    public abstract ByteBuffer encodeMessage(WebsocketOpcode opcode, byte[] msg, boolean isBinary);

    public static AbstractWebsocketHandler getHandler(WebsocketOpeningHandshakeHeader openingHandshakeHeader,
                                                      WebsocketEventCallback callback) {
        switch (openingHandshakeHeader.getProtocol()) {
            case RFC6455:
            case HYBI_07_12:
                return new HybiWebsocketHandler(openingHandshakeHeader, callback);
            case HIXIE76:
                return new HixieWebsocketHandler(openingHandshakeHeader, callback);
        }
        return null;
    }

}
