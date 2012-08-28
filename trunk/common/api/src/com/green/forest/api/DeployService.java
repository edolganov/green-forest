package com.green.forest.api;

import com.green.forest.api.exception.deploy.NoMappingAnnotationException;
import com.green.forest.api.exception.deploy.NotOneHandlerException;

public interface DeployService {
	
	void putHandler(Class<? extends Handler<?>> clazz) 
			throws NoMappingAnnotationException, NotOneHandlerException;
	
	void putInterceptor(Class<? extends Interceptor<?>> clazz)
			throws NoMappingAnnotationException;
	
	void putFilter(Class<? extends Filter> clazz);
	
	
	//in future...
	
	//void setHandlerBlocking(Class<? extends Handler<?>> clazz, boolean val);
	
	//void setInterceptorBlocking(Class<? extends Interceptor<?>> clazz, boolean val);
	
	//void setFilterBlocking(Class<? extends Filter> clazz, boolean val);
	
	//void setActionBlocking(Class<? extends Action<?, ?>> clazz, boolean val);
	
	
	//void removeHandler(Class<? extends Handler<?>> clazz);
	
	//void removeInterceptor(Class<? extends Interceptor<?>> clazz);
	
	//void removeFilter(Class<? extends Filter> clazz);
	
	

}
