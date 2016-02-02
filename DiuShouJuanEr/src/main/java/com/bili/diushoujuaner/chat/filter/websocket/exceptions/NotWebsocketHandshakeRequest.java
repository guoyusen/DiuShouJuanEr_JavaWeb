package com.bili.diushoujuaner.chat.filter.websocket.exceptions;

public class NotWebsocketHandshakeRequest extends Exception {
	private static final long serialVersionUID = 1L;

	public NotWebsocketHandshakeRequest(String message) {
        super(message);
    }
}
