package com.green.forest.api.exception.invoke;

import com.green.forest.api.Action;

public class HandlerNotFoundException extends InvocationException {
	
	public HandlerNotFoundException(Action<?,?> action) {
		super("handler not found for "+action);
	}

}
