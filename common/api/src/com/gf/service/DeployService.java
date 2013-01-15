/*
 * Copyright 2012 Evgeny Dolganov (evgenij.dolganov@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gf.service;

import java.util.Collection;

import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.exception.deploy.NoMappingAnnotationException;
import com.gf.exception.deploy.NotOneHandlerException;
import com.gf.extra.invocation.InvocationObjectInitializer;

/**
 * Interface for adding handlers, interceptors and filters.
 * Main implementaion is {@link com.gf.core.Engine}.
 *
 * @author Evgeny Dolganov
 * @see Handler
 * @see Interceptor
 * @see Filter
 * @see InvocationObjectInitializer
 * @see com.gf.core.Engine
 *
 */
public interface DeployService {
	
	/**
	 * Put the {@link Handler} class.
	 */
	void putHandler(Class<? extends Handler<?>> clazz) 
			throws NoMappingAnnotationException, NotOneHandlerException;
	
	/**
	 * Put the {@link Interceptor} class.
	 */
	void putInterceptor(Class<? extends Interceptor<?>> clazz) 
			throws NoMappingAnnotationException;
	
	/**
	 * Put the {@link Filter} class.
	 */
	void putFilter(Class<? extends Filter> clazz);
	
	/**
	 * Put {@link InvocationObjectInitializer} object
	 */
	void putInitializer(InvocationObjectInitializer initializer);
	
	
	/**
	 * Scan packages and put all handlers, interceptors, filters.
	 */
	void scanAndPut(String packageName) 
			throws NoMappingAnnotationException, NotOneHandlerException;
	
	/**
	 * Analog for {@link #scanAndPut(String)}: <tt>scanAndPut(clazz.getPackage().getName())</tt>
	 */
	void scanAndPut(Class<?> clazz)
			throws NoMappingAnnotationException, NotOneHandlerException;
	
	/**
	 * Analog of multi call of {@link #putHandler(Class)}.
	 * For example for JavaBean logic.
	 */
	void setHandlerTypes(Collection<Class<? extends Handler<?>>> handlerTypes) 
			throws NoMappingAnnotationException, NotOneHandlerException;
	
	/**
	 * Analog of multi call of {@link #putInterceptor(Class)}.
	 * For example for JavaBean logic.
	 */
	void setInterceptorTypes(Collection<Class<? extends Interceptor<?>>> interceptorTypes) 
			throws NoMappingAnnotationException;
	
	/**
	 * Analog of multi call of {@link #putFilter(Class)}.
	 * For example for JavaBean logic.
	 */
	void setFilterTypes(Collection<Class<? extends Filter>> filterTypes);
	
	/**
	 * Analog of multi call of {@link #putInitializer(InvocationObjectInitializer)}.
	 * For example for JavaBean logic.
	 */
	void setInitializers(Collection<InvocationObjectInitializer> initializers);
	
	/**
	 * Analog of multi call of {@link #scanAndPut(String)}.
	 * For example for JavaBean logic.
	 */
	void setScanAndPut(Collection<String> packageNames) 
			throws NoMappingAnnotationException, NotOneHandlerException;
	
	
	//in future...
	
	//void removeHandler(Class<? extends Handler<?>> clazz);
	
	//void removeInterceptor(Class<? extends Interceptor<?>> clazz);
	
	//void removeFilter(Class<? extends Filter> clazz);
	
	

}
