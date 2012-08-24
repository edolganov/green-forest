package com.green.forest.core.deploy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.green.forest.api.Action;
import com.green.forest.api.DeployService;
import com.green.forest.api.Filter;
import com.green.forest.api.Handler;
import com.green.forest.api.Interceptor;
import com.green.forest.api.exception.invoke.HandlerNotFoundException;
import com.green.forest.api.exception.invoke.NotOneHandlerException;
import com.green.forest.api.key.core.actionservice.TypesRepoClass;
import com.green.forest.core.CoreUtil;
import com.green.forest.core.config.ConfigService;
import com.green.forest.core.repo.TypesRepo;
import com.green.forest.util.Util;

public class DeployServiceImpl implements DeployService, ResourseService {
	
	TypesRepo handlerTypes;
	TypesRepo interceptorTypes;
	CopyOnWriteArraySet<Class<?>> filterTypes;
	
	public DeployServiceImpl(ConfigService config) {
		
		Class<?> typeRepoClass = config.getValue(new TypesRepoClass());
		
		interceptorTypes = CoreUtil.createInstance(typeRepoClass);
		interceptorTypes.setOneHandlerOnly(false);
		
		handlerTypes = CoreUtil.createInstance(typeRepoClass);
		handlerTypes.setOneHandlerOnly(true);
		
		filterTypes = new CopyOnWriteArraySet<Class<?>>();
		
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
	public Class<?> getHandlerType(Action<?,?> action)
			throws HandlerNotFoundException, NotOneHandlerException{
		
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
	public List<Filter> getFilters(Action<?, ?> action) {

		return new ArrayList<Filter>();
	}

}
