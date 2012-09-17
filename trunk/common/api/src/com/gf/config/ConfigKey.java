package com.gf.config;

public abstract class ConfigKey<T> {
	
	
	public abstract boolean hasDefaultValue();
	
	public T getDefaultValue() throws Exception {
		return null;
	}
	
	

}
