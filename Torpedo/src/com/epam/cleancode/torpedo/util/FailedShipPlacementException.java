package com.epam.cleancode.torpedo.util;

public class FailedShipPlacementException extends Exception {

	private static final long serialVersionUID = 382996118140847367L;

	public FailedShipPlacementException() {
		super();
	}
	
	public FailedShipPlacementException(String message) {
		super(message);
	}
	
	public FailedShipPlacementException(String message, Throwable cause) {
		super(message, cause);
	}

	public FailedShipPlacementException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}	

	public FailedShipPlacementException(Throwable cause) {
		super(cause);
	}
}
