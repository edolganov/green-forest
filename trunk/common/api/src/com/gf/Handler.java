package com.gf;



public abstract class Handler<T extends Action<?,?>> extends MappingObject {
	
	
	public abstract void invoke(T action) throws Exception;
	
}
