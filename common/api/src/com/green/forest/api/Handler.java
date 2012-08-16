package com.green.forest.api;


public abstract class Handler<T extends Action<?,?>> extends MappingObject {
	
	public abstract void invoke(T action) throws Exception;
	
}
