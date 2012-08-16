package com.green.forest.api.exception.invoke;

import com.green.forest.api.exception.BaseException;

public abstract class InvocationException extends BaseException {

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
