package com.gf.exception.invoke;

import com.gf.Action;

public class HandlerNotFoundException extends InvocationException {
	
	public HandlerNotFoundException(Action<?,?> action) {
		super("handler not found for "+action);
	}

}
