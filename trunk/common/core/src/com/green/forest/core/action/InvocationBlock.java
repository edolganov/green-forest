package com.green.forest.core.action;

import java.io.Serializable;
import java.util.List;

import com.green.forest.api.Action;
import com.green.forest.api.key.core.action.TraceHandlers;
import com.green.forest.core.action.filter.FiltersBlock;
import com.green.forest.core.action.interceptor.InterceptorsBlock;


public class InvocationBlock {
	
	ActionServiceImpl actionService;
	Action<?,?> action;
	
	
	public InvocationBlock(ActionServiceImpl actionService, Action<?,?> action){
		this.actionService = actionService;
		this.action = action;
	}
	
	public void invoke() {
		
		InvocationContext c = createContext(action, null, true);
		
		FiltersBlock block = new FiltersBlock(c);
		block.invoke();
		
	}
	
	
	<I extends Serializable, O extends Serializable> O subInvoke(
			InvocationContext parent,
			Action<I, O> action) throws Exception {
		
		InvocationContext c = createContext(action, parent, false);
		
		InterceptorsBlock block = new InterceptorsBlock(c);
		block.invoke();
		
		return (O) action.getOutput();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private InvocationContext createContext(Action<?,?> action, InvocationContext parent, boolean initFilters){
		InvocationContext c = new InvocationContext();
		c.owner = this;
		c.parent = parent;
		c.actions = actionService;
		c.action = action;
		
		//prepare services
		c.config = c.actions.config;
		
		//prepare flags
		c.isTraceHandlers = c.config.isTrue(new TraceHandlers());
		
		//prepare handlers
		if(initFilters){
			c.filters = c.actions.resourse.getFilters();
		}
		c.interceptors = (List)c.actions.resourse.getInterceptors(c.action);
		c.handler = c.actions.resourse.getHandler(c.action);
		
		return c;
	}

}
