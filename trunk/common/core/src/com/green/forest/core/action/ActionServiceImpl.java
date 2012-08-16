package com.green.forest.core.action;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.green.forest.api.Action;
import com.green.forest.api.ActionService;
import com.green.forest.api.Filter;
import com.green.forest.api.Handler;
import com.green.forest.api.Interceptor;
import com.green.forest.api.exception.BaseException;
import com.green.forest.api.exception.UnexpectedException;
import com.green.forest.api.exception.invoke.NullActionException;
import com.green.forest.api.key.core.actionservice.TypesRepoClass;
import com.green.forest.core.CoreUtil;
import com.green.forest.core.config.ConfigService;
import com.green.forest.core.context.StaticContext;
import com.green.forest.core.repo.TypesRepo;
import com.green.forest.util.Util;

public class ActionServiceImpl implements ActionService {
	
	private ConfigService config;
	
	StaticContext staticContext = new StaticContext();
	
	Set<Class<?>> filterTypes;
	TypesRepo interceptorTypes;
	TypesRepo handlerTypes;
	
	public ActionServiceImpl(ConfigService configService) {
		
		this.config = configService;
		
		Class<?> typeRepoClass = config.getValue(new TypesRepoClass());
		
		filterTypes = new HashSet<Class<?>>();
		
		interceptorTypes = CoreUtil.createInstance(typeRepoClass);
		interceptorTypes.setOneHandlerOnly(false);
		
		handlerTypes = CoreUtil.createInstance(typeRepoClass);
		handlerTypes.setOneHandlerOnly(true);
			
		
	}
	
	
	public void putHandler(Class<? extends Handler<Action<?,?>>> clazz){
		handlerTypes.put(clazz);
	}
	
	public void putInterceptor(Class<? extends Interceptor<Action<?,?>>> clazz){
		interceptorTypes.put(clazz);
	}
	
	public void putFilter(Class<? extends Filter> clazz){
		filterTypes.add(clazz);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public <I extends Serializable, O extends Serializable> O invoke(Action<I, O> action) {
		
		try {
			
			if(Util.isEmpty(action)){
				throw new NullActionException();
			}
			
			InvocationBlock block = new InvocationBlock(this);
			Object out = block.invoke(action);
			return (O)out;
			
		} catch (BaseException e) {
			throw e;
		} catch (Exception e) {
			throw new UnexpectedException(e);
		}

	}

}
