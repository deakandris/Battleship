package com.epam.cleancode.torpedo.util;

public class MalformedMessageException extends Exception {

	private static final long serialVersionUID = 5295747221697773646L;

	public MalformedMessageException() {
		super();
	}

	public MalformedMessageException(String message) {
		super(message);
	}

	public MalformedMessageException(String message, Throwable cause) {
		super(message, cause);
	}

	public MalformedMessageException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MalformedMessageException(Throwable cause) {
		super(cause);
	}
}
