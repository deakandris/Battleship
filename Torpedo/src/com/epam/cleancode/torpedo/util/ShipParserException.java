package com.epam.cleancode.torpedo.util;

public class ShipParserException extends RuntimeException {

	private static final long serialVersionUID = 1522317864001852236L;

	public ShipParserException() {
		super();
	}
	
	public ShipParserException(String message) {
		super(message);
	}
	
	public ShipParserException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShipParserException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}	

	public ShipParserException(Throwable cause) {
		super(cause);
	}
}
