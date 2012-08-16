package com.green.forest.core.action;

import java.util.Set;

import com.green.forest.api.Action;
import com.green.forest.api.exception.invoke.HandlerNotFoundException;
import com.green.forest.api.exception.invoke.NotOneHandlerException;
import com.green.forest.core.context.InvocationContext;
import com.green.forest.util.Util;


public class InvocationBlock {
	
	ActionServiceImpl owner;
	InvocationContext context = new InvocationContext();
	
	public InvocationBlock(ActionServiceImpl owner){
		this.owner = owner;
	}
	
	public Object invoke(Action<?,?> action) {
		
		context.handlerType = getHandlerType(action);
		context.addAll(owner.staticContext);
		
		invokeFilterBlock(action);
		Object out = invokeMappingBlock(action);
		return out;
	}


	private Class<?> getHandlerType(Action<?,?> action) {
		
		Class<?> clazz = action.getClass();
		Set<Class<?>> handlers = owner.handlerTypes.getTypes(clazz);
		
		if(Util.isEmpty(handlers)){
			throw new HandlerNotFoundException(action);
		}
		
		if(handlers.size() > 1){
			throw new NotOneHandlerException(action, handlers);
		}
		
		Class<?> out = handlers.iterator().next();
		return out;
	}
	
	private void invokeFilterBlock(Action<?,?> action){
		FilterBlock block = new FilterBlock(this);
		block.invoke(action);
	}
	

	private Object invokeMappingBlock(Action<?,?> action) {
		// TODO Auto-generated method stub
		return null;
	}

}
