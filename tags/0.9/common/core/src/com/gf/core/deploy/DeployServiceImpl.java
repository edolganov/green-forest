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
package com.gf.core.deploy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import com.gf.Action;
import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.InvocationObject;
import com.gf.annotation.Mapping;
import com.gf.core.util.CoreUtil;
import com.gf.exception.InvalidStateException;
import com.gf.exception.deploy.NoMappingAnnotationException;
import com.gf.exception.invoke.HandlerNotFoundException;
import com.gf.exception.invoke.NotOneHandlerException;
import com.gf.extra.invocation.InvocationObjectInitializer;
import com.gf.extra.scan.ClassScanner;
import com.gf.key.scan.ClassScannerKey;
import com.gf.log.Log;
import com.gf.log.LogFactory;
import com.gf.service.ConfigService;
import com.gf.service.DeployService;
import com.gf.util.Util;

public class DeployServiceImpl implements DeployService, ResourseService {
	
	Log log = LogFactory.getLog(getClass()); 
	
	ConfigService config;
	
	TypesRepository handlerTypes;
	TypesRepository interceptorTypes;
	CopyOnWriteArraySet<Class<?>> filterTypes = new CopyOnWriteArraySet<Class<?>>();
	OrderComparator orderComparator = new OrderComparator();
	
	CopyOnWriteArrayList<InvocationObjectInitializer> initializers = new CopyOnWriteArrayList<InvocationObjectInitializer>();
	
	
	
	public DeployServiceImpl(ConfigService config) {
		
		this.config = config;
		
		interceptorTypes = new TypesRepositoryImpl();
		interceptorTypes.setOneHandlerOnly(false);
		
		handlerTypes = new TypesRepositoryImpl();
		handlerTypes.setOneHandlerOnly(true);

	}

	public void putHandler(Class<? extends Handler<?>> clazz) {
		Set<Class<?>> targets = handlerTypes.put(clazz);
		logMapping("PUT HANDLER:", clazz, targets);
	}

	public void putInterceptor(Class<? extends Interceptor<?>> clazz) {
		Set<Class<?>> targets = interceptorTypes.put(clazz);
		logMapping("PUT INTERCEPTOR:", clazz, targets);
	}

	public void putFilter(Class<? extends Filter> clazz) {
		filterTypes.add(clazz);
		logMappingSingle("PUT FILTER:", clazz, null);
	}
	
	
	public void scanAndPut(Class<?> clazz){
		scanAndPut(clazz.getPackage().getName());
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void scanAndPut(String packageName) {
		
		log.info("Scanning and putting classes...");
		
		
		Class<?> scannerType = config.getConfig(new ClassScannerKey());
		ClassScanner scanner = CoreUtil.createInstance(scannerType);
	    Set<Class<?>> mapperSet = scanner.getClasses(packageName, InvocationObject.class);
	    if(mapperSet == null){
	    	mapperSet = Collections.emptySet();
	    }
	    
	    int totalFilters = 0;
	    int totalInterceptors = 0;
	    int totalHandlers = 0;
	    for (Class candidatClass : mapperSet) {
	    	
	    	if(Filter.class.isAssignableFrom(candidatClass)){
    			putFilter(candidatClass);
    			totalFilters++;
    			continue;
    		}
	    	
	    	Mapping mapping = (Mapping)candidatClass.getAnnotation(Mapping.class);
	    	if(mapping != null){
	    		if(Handler.class.isAssignableFrom(candidatClass)){
		    		putHandler(candidatClass);
		    		totalHandlers++;
	    		}
	    		else if(Interceptor.class.isAssignableFrom(candidatClass)){
	    			putInterceptor(candidatClass);
	    			totalInterceptors++;
	    		}
	    	}
	    }
	    
	    log.info("Done. [filters:"+totalFilters
	    		+", interceptors:"+totalInterceptors
	    		+", handlers:"+totalHandlers+"]");
	}

	
	public Handler<?> getHandler(Action<?, ?> action) {
		
		Class<?> handlerType = getHandlerType(action);
		Handler<?> out = null;
		
		try {
			Object newInstance = handlerType.newInstance();
			out = (Handler<?>) newInstance;
		}catch (Exception e) {
			throw new InvalidStateException("can't create handler by "+handlerType, e);
		}
		
		return out;
	}
	
	private Class<?> getHandlerType(Action<?,?> action){
		
		Class<?> clazz = action.getClass();
		Set<Class<?>> handlers = handlerTypes.getTypes(clazz);
		
		if(Util.isEmpty(handlers)){
			throw new HandlerNotFoundException(action);
		}
		
		if(handlers.size() > 1){
			throw new NotOneHandlerException(action, handlers);
		}
		
		Class<?> out = handlers.iterator().next();
		return out;
	}

	public List<Filter> getFilters() {
		
		ArrayList<Filter> out = new ArrayList<Filter>();
		
		for(Class<?> filterType : filterTypes){
			try {
				Object newInstance = filterType.newInstance();
				Filter filter = (Filter) newInstance;
				out.add(filter);
			}catch (Exception e) {
				throw new InvalidStateException("can't create filter by "+filterType, e);
			}
		}
		
		Collections.sort(out, orderComparator);
		
		return out;
	}
	
	public List<Interceptor<?>> getInterceptors(Action<?, ?> action) {
		
		Class<?> clazz = action.getClass();
		Set<Class<?>> set = interceptorTypes.getTypes(clazz);
		
		ArrayList<Interceptor<?>> out = new ArrayList<Interceptor<?>>();
		
		for(Class<?> interceptorType : set){
			try {
				Object newInstance = interceptorType.newInstance();
				Interceptor<?> interceptor = (Interceptor<?>) newInstance;
				out.add(interceptor);
			}catch (Exception e) {
				throw new InvalidStateException("can't create interceptor by "+interceptorType, e);
			}
		}
		
		
		Collections.sort(out, orderComparator);
		
		return out;
	}

	public void setHandlerTypes(
			Collection<Class<? extends Handler<?>>> handlerTypes)
			throws NoMappingAnnotationException, NotOneHandlerException {
		if(handlerTypes == null) return;
		for (Class<? extends Handler<?>> clazz : handlerTypes) {
			putHandler(clazz);
		}
	}

	public void setInterceptorTypes(
			Collection<Class<? extends Interceptor<?>>> interceptorTypes)
			throws NoMappingAnnotationException {
		if(interceptorTypes == null) return;
		for (Class<? extends Interceptor<?>> clazz : interceptorTypes) {
			putInterceptor(clazz);
		}
	}

	public void setFilterTypes(Collection<Class<? extends Filter>> filterTypes) {
		if(filterTypes == null) return;
		for (Class<? extends Filter> clazz : filterTypes) {
			putFilter(clazz);
		}
	}

	public void setScanAndPut(Collection<String> packageNames)
			throws NoMappingAnnotationException, NotOneHandlerException {
		if(packageNames == null) return;
		for (String packageName : packageNames) {
			scanAndPut(packageName);
		}
	}
	
	
	private void logMapping(String preffix, Class<?> handler, Set<Class<?>> targets) {
		for (Class<?> target : targets) {
			logMappingSingle(preffix, handler, target);
		}
		
	}

	public void putInitializer(InvocationObjectInitializer initializer) {
		initializers.add(initializer);
		logMappingSingle("PUT INITIALIZER:", initializer.getClass(), null);
	}

	public void setInitializers(
			Collection<InvocationObjectInitializer> initializers) {
		if(initializers == null) return;
		for (InvocationObjectInitializer initializer : initializers) {
			putInitializer(initializer);
		}
	}

	public List<InvocationObjectInitializer> getInitializers() {
		return initializers;
	}
	
	private void logMappingSingle(String preffix, Class<?> handler, Class<?> target) {
		String handlerPart = "["+handler.getName()+"]";
		String targetPart = target != null? " -> [" + target.getName() + "]" : "";
		String msg = preffix+" "+ handlerPart + targetPart;
		log.info(msg);
	}

	

}
