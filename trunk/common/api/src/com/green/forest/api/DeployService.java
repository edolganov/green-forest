package com.green.forest.api;

import com.green.forest.api.exception.deploy.NoMappingAnnotationException;
import com.green.forest.api.exception.deploy.NotOneHandlerException;

public interface DeployService {
	
	void putHandler(Class<? extends Handler<?>> clazz) 
			throws NoMappingAnnotationException, NotOneHandlerException;
	
	void putInterceptor(Class<? extends Interceptor<?>> clazz)
			throws NoMappingAnnotationException;
	
	void putFilter(Class<? extends Filter> clazz);

}
