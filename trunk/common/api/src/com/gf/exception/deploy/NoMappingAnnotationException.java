package com.gf.exception.deploy;

public class NoMappingAnnotationException extends DeployException {
	
	public NoMappingAnnotationException(Class<?> handler) {
		super("no mapping for ["+handler+"]");
	}

}
