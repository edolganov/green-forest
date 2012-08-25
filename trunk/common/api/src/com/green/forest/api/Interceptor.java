package com.green.forest.api;

public abstract class Interceptor<T extends Action<?,?>> extends MappingObject {
	
	public abstract void invoke(T action, InterceptorChain chain) throws Exception;

}
