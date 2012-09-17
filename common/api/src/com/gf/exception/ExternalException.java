package com.gf.exception;

public class ExternalException extends BaseException {

	public ExternalException() {
		super();
	}

	public ExternalException(String message, Exception cause) {
		super(message, cause);
	}
	
	public Exception getOriginal(){
		return (Exception) getCause();
	}

}
