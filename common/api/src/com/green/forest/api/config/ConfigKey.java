package com.green.forest.api.config;

public abstract class ConfigKey<T> {
	
	
	public abstract boolean hasDefaultValue();
	
	public T getDefaultValue(){
		return null;
	}
	
	

}
