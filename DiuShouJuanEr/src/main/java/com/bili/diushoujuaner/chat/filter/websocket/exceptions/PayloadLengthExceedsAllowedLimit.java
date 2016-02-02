package com.bili.diushoujuaner.chat.filter.websocket.exceptions;

public class PayloadLengthExceedsAllowedLimit extends Exception {
	private static final long serialVersionUID = 1L;

	public PayloadLengthExceedsAllowedLimit(String message) {
        super(message);
    }
}
