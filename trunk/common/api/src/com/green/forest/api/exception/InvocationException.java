package com.green.forest.api.exception;

public abstract class InvocationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvocationException() {
		super();
	}

	public InvocationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvocationException(String message) {
		super(message);
	}

	public InvocationException(Throwable cause) {
		super(cause);
	}
	
	

}
