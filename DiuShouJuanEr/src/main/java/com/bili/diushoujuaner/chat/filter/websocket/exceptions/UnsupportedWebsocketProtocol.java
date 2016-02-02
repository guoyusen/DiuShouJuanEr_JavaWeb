package com.bili.diushoujuaner.chat.filter.websocket.exceptions;

public class UnsupportedWebsocketProtocol extends Exception {
	private static final long serialVersionUID = 1L;

	public UnsupportedWebsocketProtocol(String message) {
        super(message);
    }
}
