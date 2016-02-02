package com.bili.diushoujuaner.chat.filter.websocket.dto;

import static java.nio.charset.StandardCharsets.UTF_8;

public class WebsocketMessage {
    private WebsocketOpcode opcode;

    private boolean isBinary;

    private byte[] payload;

    public WebsocketMessage(WebsocketOpcode opcode, boolean binary, byte[] payload) {
        this.opcode = opcode;
        this.isBinary = binary;
        this.payload = payload;
    }

    public WebsocketOpcode getOpcode() {
        return opcode;
    }

    public boolean isBinary() {
        return isBinary;
    }

    public byte[] getPayload() {
        return payload;
    }

    public String getPayloadAsString() {
        return new String(payload, UTF_8);
    }

    @Override
    public String toString() {
        return "WebsocketMessage :: opcode = " + opcode + ", isBinary = " + isBinary + ", payload = " + payload;
    }
}
