package com.green.forest.api.exception;

public class UnexpectedException extends BaseException {

	public UnexpectedException() {
		super();
	}

	public UnexpectedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnexpectedException(String message) {
		super(message);
	}

	public UnexpectedException(Throwable cause) {
		super(cause);
	}
	
	

}
