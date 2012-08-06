package com.green.forest.api.exception;

public abstract class GreenForestException extends Exception {

	private static final long serialVersionUID = 1L;

	public GreenForestException() {
		super();
	}

	public GreenForestException(String message, Throwable cause) {
		super(message, cause);
	}

	public GreenForestException(String message) {
		super(message);
	}

	public GreenForestException(Throwable cause) {
		super(cause);
	}
	
	

}
