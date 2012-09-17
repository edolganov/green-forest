package com.gf.exception.invoke;

import com.gf.exception.BaseException;

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
