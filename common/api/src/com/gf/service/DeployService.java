package com.gf.service;

import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.exception.deploy.NoMappingAnnotationException;
import com.gf.exception.deploy.NotOneHandlerException;

public interface DeployService {
	
	void putHandler(Class<? extends Handler<?>> clazz) 
			throws NoMappingAnnotationException, NotOneHandlerException;
	
	void putInterceptor(Class<? extends Interceptor<?>> clazz)
			throws NoMappingAnnotationException;
	
	void putFilter(Class<? extends Filter> clazz);
	
	void scanForAnnotations(Package pckg);
	
	void scanForAnnotations(String packageName);
	
	
	//in future...
	
	//void setHandlerBlocking(Class<? extends Handler<?>> clazz, boolean val);
	
	//void setInterceptorBlocking(Class<? extends Interceptor<?>> clazz, boolean val);
	
	//void setFilterBlocking(Class<? extends Filter> clazz, boolean val);
	
	//void setActionBlocking(Class<? extends Action<?, ?>> clazz, boolean val);
	
	
	//void removeHandler(Class<? extends Handler<?>> clazz);
	
	//void removeInterceptor(Class<? extends Interceptor<?>> clazz);
	
	//void removeFilter(Class<? extends Filter> clazz);
	
	

}
