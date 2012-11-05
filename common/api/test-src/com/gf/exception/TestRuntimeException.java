package com.gf.exception;

public class TestRuntimeException extends RuntimeException {

	public TestRuntimeException() {
		super();
	}

	public TestRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public TestRuntimeException(String message) {
		super(message);
	}

	public TestRuntimeException(Throwable cause) {
		super(cause);
	}
	
	

}
