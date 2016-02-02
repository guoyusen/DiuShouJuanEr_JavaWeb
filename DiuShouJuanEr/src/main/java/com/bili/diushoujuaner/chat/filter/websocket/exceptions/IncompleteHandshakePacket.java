package com.bili.diushoujuaner.chat.filter.websocket.exceptions;

public class IncompleteHandshakePacket extends Exception {
	private static final long serialVersionUID = 1L;

	public IncompleteHandshakePacket(String message) {
        super(message);
    }
}
