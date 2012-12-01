package com.gf.service;

import java.util.Collection;

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
	
	void scanForAnnotations(Package pckg) 
			throws NoMappingAnnotationException, NotOneHandlerException;
	
	void scanForAnnotations(String packageName) 
			throws NoMappingAnnotationException, NotOneHandlerException;
	
	void scanPackageForAnnotations(Object obj)
			throws NoMappingAnnotationException, NotOneHandlerException;
	
	void scanPackageForAnnotations(Class<?> clazz)
			throws NoMappingAnnotationException, NotOneHandlerException;
	
	
	void setHandlerTypes(Collection<Class<? extends Handler<?>>> handlerTypes) 
			throws NoMappingAnnotationException, NotOneHandlerException;
	
	void setInterceptorTypes(Collection<Class<? extends Interceptor<?>>> interceptorTypes) 
			throws NoMappingAnnotationException;
	
	void setFilterTypes(Collection<Class<? extends Filter>> filterTypes);
	
	void setScanForAnnotationsPackages(Collection<String> packageNames) 
			throws NoMappingAnnotationException, NotOneHandlerException;
	
	
	//in future...
	
	//void removeHandler(Class<? extends Handler<?>> clazz);
	
	//void removeInterceptor(Class<? extends Interceptor<?>> clazz);
	
	//void removeFilter(Class<? extends Filter> clazz);
	
	

}
