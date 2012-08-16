package com.green.forest.api.exception.invoke;

import java.util.Set;

import com.green.forest.api.Action;

public class NotOneHandlerException extends InvocationException {
	
	public NotOneHandlerException(Action<?,?> action, Set<Class<?>> handlers) {
		super("target action has many handlers "+handlers);
	}

}
