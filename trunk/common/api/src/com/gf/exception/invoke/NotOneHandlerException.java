package com.gf.exception.invoke;

import java.util.Set;

import com.gf.Action;

public class NotOneHandlerException extends InvocationException {
	
	public NotOneHandlerException(Action<?,?> action, Set<Class<?>> handlers) {
		super("target action has many handlers "+handlers);
	}

}
