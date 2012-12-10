package com.gf.core.repo;

import java.util.Set;

import com.gf.exception.deploy.NoMappingAnnotationException;
import com.gf.exception.deploy.NotOneHandlerException;

public interface TypesRepository {
	
	void setOneHandlerOnly(boolean val) throws NotOneHandlerException;
	
	boolean isOneHandlerOnly();
	
	Set<Class<?>> put(Class<?> handler) throws NoMappingAnnotationException, NotOneHandlerException;
	
	Set<Class<?>> getTypes(Class<?> target);

}
