package com.gf.core.deploy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.gf.Action;
import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.InvocationObject;
import com.gf.annotation.Mapping;
import com.gf.core.util.CoreUtil;
import com.gf.exception.ExceptionWrapper;
import com.gf.exception.deploy.NoMappingAnnotationException;
import com.gf.exception.invoke.HandlerNotFoundException;
import com.gf.exception.invoke.NotOneHandlerException;
import com.gf.extra.scan.ClassScanner;
import com.gf.key.scan.ClassScannerKey;
import com.gf.log.Log;
import com.gf.log.LogFactory;
import com.gf.service.ConfigService;
import com.gf.service.DeployService;
import com.gf.util.Util;

public class DeployServiceImpl implements DeployService, ResourseService {
	
	Log log = LogFactory.getLog(getClass()); 
	
	TypesRepository handlerTypes;
	TypesRepository interceptorTypes;
	CopyOnWriteArraySet<Class<?>> filterTypes = new CopyOnWriteArraySet<Class<?>>();
	OrderComparator orderComparator = new OrderComparator();
	ClassScanner scanner;
	
	public DeployServiceImpl(ConfigService config) {
		
		interceptorTypes = new TypesRepositoryImpl();
		interceptorTypes.setOneHandlerOnly(false);
		
		handlerTypes = new TypesRepositoryImpl();
		handlerTypes.setOneHandlerOnly(true);
		
		Class<?> type = config.getConfig(new ClassScannerKey());
		scanner = CoreUtil.createInstance(type);

	}

	@Override
	public void putHandler(Class<? extends Handler<?>> clazz) {
		Set<Class<?>> targets = handlerTypes.put(clazz);
		logMapping("PUT HANDLER:", clazz, targets);
	}

	@Override
	public void putInterceptor(Class<? extends Interceptor<?>> clazz) {
		Set<Class<?>> targets = interceptorTypes.put(clazz);
		logMapping("PUT INTERCEPTOR:", clazz, targets);
	}

	@Override
	public void putFilter(Class<? extends Filter> clazz) {
		filterTypes.add(clazz);
		logMappingSingle("PUT FILTER:", clazz, null);
	}
	
	@Override
	public void scanPackageForAnnotations(Object obj){
		scanForAnnotations(obj.getClass().getPackage());
	}
	
	@Override
	public void scanPackageForAnnotations(Class<?> clazz){
		scanForAnnotations(clazz.getPackage());
	}
	
	@Override
	public void scanForAnnotations(Package pckg) {
		scanForAnnotations(pckg.getName());
	}
	

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void scanForAnnotations(String packageName) {
		
		log.info("Scanning and registering classes...");
		
	    Set<Class<?>> mapperSet = scanner.getClasses(packageName, InvocationObject.class);
	    
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

	
	@Override
	public Handler<?> getHandler(Action<?, ?> action) {
		
		Class<?> handlerType = getHandlerType(action);
		Handler<?> out = null;
		
		try {
			Object newInstance = handlerType.newInstance();
			out = (Handler<?>) newInstance;
		}catch (Exception e) {
			throw new ExceptionWrapper("can't create handler by "+handlerType, e);
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

	@Override
	public List<Filter> getFilters() {
		
		ArrayList<Filter> out = new ArrayList<Filter>();
		
		for(Class<?> filterType : filterTypes){
			try {
				Object newInstance = filterType.newInstance();
				Filter filter = (Filter) newInstance;
				out.add(filter);
			}catch (Exception e) {
				throw new ExceptionWrapper("can't create filter by "+filterType, e);
			}
		}
		
		Collections.sort(out, orderComparator);
		
		return out;
	}
	
	@Override
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
				throw new ExceptionWrapper("can't create interceptor by "+interceptorType, e);
			}
		}
		
		
		Collections.sort(out, orderComparator);
		
		return out;
	}

	@Override
	public void setHandlerTypes(
			Collection<Class<? extends Handler<?>>> handlerTypes)
			throws NoMappingAnnotationException, NotOneHandlerException {
		if(handlerTypes == null) return;
		for (Class<? extends Handler<?>> clazz : handlerTypes) {
			putHandler(clazz);
		}
	}

	@Override
	public void setInterceptorTypes(
			Collection<Class<? extends Interceptor<?>>> interceptorTypes)
			throws NoMappingAnnotationException {
		if(interceptorTypes == null) return;
		for (Class<? extends Interceptor<?>> clazz : interceptorTypes) {
			putInterceptor(clazz);
		}
	}

	@Override
	public void setFilterTypes(Collection<Class<? extends Filter>> filterTypes) {
		if(filterTypes == null) return;
		for (Class<? extends Filter> clazz : filterTypes) {
			putFilter(clazz);
		}
	}

	@Override
	public void setScanForAnnotationsPackages(Collection<String> packageNames)
			throws NoMappingAnnotationException, NotOneHandlerException {
		if(packageNames == null) return;
		for (String packageName : packageNames) {
			scanForAnnotations(packageName);
		}
	}
	
	
	private void logMapping(String preffix, Class<?> handler, Set<Class<?>> targets) {
		for (Class<?> target : targets) {
			logMappingSingle(preffix, handler, target);
		}
		
	}

	private void logMappingSingle(String preffix, Class<?> handler, Class<?> target) {
		String handlerPart = "["+handler.getName()+"]";
		String targetPart = target != null? "-> [" + target.getName() + "]" : "";
		String msg = preffix+" "+ handlerPart + targetPart;
		log.info(msg);
	}

	

}
