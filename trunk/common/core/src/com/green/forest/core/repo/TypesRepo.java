package com.green.forest.core.repo;

import java.util.Set;

import com.green.forest.api.exception.deploy.NoMappingAnnotationException;
import com.green.forest.api.exception.deploy.NotOneHandlerException;

public interface TypesRepo {
	
	void setOneHandlerOnly(boolean val) throws NotOneHandlerException;
	
	boolean isOneHandlerOnly();
	
	void put(Class<?> handler) throws NoMappingAnnotationException, NotOneHandlerException;
	
	Set<Class<?>> getTypes(Class<?> target);

}
