package com.green.forest.api.exception.deploy;

import java.util.Set;

public class NotOneHandlerException extends DeployException {
	
	public NotOneHandlerException(Class<?> target, Set<Class<?>> handlers) {
		super("target ["+target+"] has many handlers "+handlers);
	}
	
	

}
