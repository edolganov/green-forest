package com.gf.exception.invoke;

public class InjectException extends InvocationException {

	public InjectException(Object ob, Throwable cause) {
		super("can't inject data to object "+ob, cause);
	}
	
	

}
