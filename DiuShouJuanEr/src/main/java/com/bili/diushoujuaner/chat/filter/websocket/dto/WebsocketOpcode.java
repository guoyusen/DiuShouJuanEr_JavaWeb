package com.bili.diushoujuaner.chat.filter.websocket.dto;

public enum WebsocketOpcode {
    CONTINUATION((byte) 0x0, false),
    TEXT((byte) 0x1, false),
    BINARY((byte) 0x2,false),
    RESERVED((byte) 0x3, false),
    CLOSE((byte) 0x8, true),
    PING((byte) 0x9, true),
    PONG((byte) 0xA, true);

    private byte opcode;
    private boolean isControlOpcode;
    private static WebsocketOpcode[] index;

    static {
        index = new WebsocketOpcode[256];
        for (WebsocketOpcode websocketOpcode : WebsocketOpcode.values()) {
            index[websocketOpcode.opcode()] = websocketOpcode;
        }
    }

    WebsocketOpcode(byte opcode, boolean isControlOpcode) {
        this.opcode = opcode;
        this.isControlOpcode = isControlOpcode;
    }

    public byte opcode() {
        return opcode;
    }

    public static WebsocketOpcode get(byte opcode) {
        if(null == index[opcode])
            return RESERVED;
        return index[opcode];
    }

    public boolean isControlOpcode() {
        return isControlOpcode;
    }
}
