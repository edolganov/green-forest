package com.gf.core.deploy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.gf.Action;
import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.annotation.Mapping;
import com.gf.core.repo.TypesRepo;
import com.gf.core.util.CoreUtil;
import com.gf.core.util.ScanUtil;
import com.gf.exception.ExceptionWrapper;
import com.gf.exception.invoke.HandlerNotFoundException;
import com.gf.exception.invoke.NotOneHandlerException;
import com.gf.key.core.actionservice.TypesRepoClass;
import com.gf.log.Log;
import com.gf.log.LogFactory;
import com.gf.service.ConfigService;
import com.gf.service.DeployService;
import com.gf.util.Util;

public class DeployServiceImpl implements DeployService, ResourseService {
	
	Log log = LogFactory.getLog(getClass()); 
	
	TypesRepo handlerTypes;
	TypesRepo interceptorTypes;
	CopyOnWriteArraySet<Class<?>> filterTypes = new CopyOnWriteArraySet<Class<?>>();
	OrderComparator orderComparator = new OrderComparator();
	
	public DeployServiceImpl(ConfigService config) {
		
		Class<?> typeRepoClass = config.getValue(new TypesRepoClass());
		
		interceptorTypes = CoreUtil.createInstance(typeRepoClass);
		interceptorTypes.setOneHandlerOnly(false);
		
		handlerTypes = CoreUtil.createInstance(typeRepoClass);
		handlerTypes.setOneHandlerOnly(true);

	}

	@Override
	public void putHandler(Class<? extends Handler<?>> clazz) {
		handlerTypes.put(clazz);
	}

	@Override
	public void putInterceptor(Class<? extends Interceptor<?>> clazz) {
		interceptorTypes.put(clazz);
	}

	@Override
	public void putFilter(Class<? extends Filter> clazz) {
		filterTypes.add(clazz);
	}
	
	@Override
	public void scanForAnnotations(Package pckg) {
		scanForAnnotations(pckg.getName());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void scanForAnnotations(String packageName) {
		
		log.info("Scanning and registering classes with @Mapping annotation...");
		
		ScanUtil<Class> scanUtil = new ScanUtil<Class>();
	    scanUtil.find(new ScanUtil.IsA(Object.class), packageName);
	    Set<Class<? extends Class>> mapperSet = scanUtil.getClasses();
	    
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

	

}
