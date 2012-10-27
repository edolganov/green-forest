package com.gf.exception;

public class ExceptionWrapper extends BaseException {

	public ExceptionWrapper(String message, Exception cause) {
		super(message, cause);
	}
	
	public Exception getOriginal(){
		return (Exception) getCause();
	}

}
