package com.green.forest.core.deploy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.green.forest.api.Action;
import com.green.forest.api.ConfigService;
import com.green.forest.api.DeployService;
import com.green.forest.api.Filter;
import com.green.forest.api.Handler;
import com.green.forest.api.Interceptor;
import com.green.forest.api.exception.ExternalException;
import com.green.forest.api.exception.invoke.HandlerNotFoundException;
import com.green.forest.api.exception.invoke.NotOneHandlerException;
import com.green.forest.api.key.core.actionservice.TypesRepoClass;
import com.green.forest.core.CoreUtil;
import com.green.forest.core.repo.TypesRepo;
import com.green.forest.util.Util;

public class DeployServiceImpl implements DeployService, ResourseService {
	
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
	public Handler<?> getHandler(Action<?, ?> action) {
		
		Class<?> handlerType = getHandlerType(action);
		Handler<?> out = null;
		
		try {
			Object newInstance = handlerType.newInstance();
			out = (Handler<?>) newInstance;
		}catch (Exception e) {
			throw new ExternalException("can't create handler by "+handlerType, e);
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
				throw new ExternalException("can't create filter by "+filterType, e);
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
				throw new ExternalException("can't create interceptor by "+interceptorType, e);
			}
		}
		
		
		Collections.sort(out, orderComparator);
		
		return out;
	}
	
	
	
	
	


	@Override
	public void setHandlerBlocking(Class<? extends Handler<?>> clazz,
			boolean val) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setInterceptorBlocking(Class<? extends Interceptor<?>> clazz,
			boolean val) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setFilterBlocking(Class<? extends Filter> clazz, boolean val) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setActionBlocking(Class<? extends Action<?, ?>> clazz,
			boolean val) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeHandler(Class<? extends Handler<?>> clazz) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeInterceptor(Class<? extends Interceptor<?>> clazz) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeFilter(Class<? extends Filter> clazz) {
		throw new UnsupportedOperationException();
	}

}
