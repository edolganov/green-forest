package com.green.forest.core.action;

import java.util.List;

import com.green.forest.api.Action;
import com.green.forest.api.key.core.action.TraceHandlers;
import com.green.forest.core.action.filter.FiltersBlock;


public class InvocationBlock {
	
	InvocationContext c;
	
	public InvocationBlock(ActionServiceImpl actionService, Action<?,?> action){
		
		c = new InvocationContext();
		c.actions = actionService;
		c.action = action;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void invoke() {
		
		//prepare services
		c.config = c.actions.config;
		
		//prepare flags
		c.isTraceHandlers = c.config.isTrue(new TraceHandlers());
		
		//prepare handlers
		c.filters = c.actions.resourse.getFilters(c.action);
		c.interceptors = (List)c.actions.resourse.getInterceptors(c.action);
		c.handler = c.actions.resourse.getHandler(c.action);
		
		//invocation
		FiltersBlock block = new FiltersBlock(c);
		block.invoke();
		
	}

}
