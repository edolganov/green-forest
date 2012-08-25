package com.green.forest.core.action;

import java.util.List;

import com.green.forest.api.Action;
import com.green.forest.api.Filter;
import com.green.forest.api.Handler;
import com.green.forest.api.Interceptor;
import com.green.forest.core.action.filter.FiltersBlock;


public class InvocationBlock {
	
	InvocationContext c;
	
	public InvocationBlock(ActionServiceImpl actionService, Action<?,?> action){
		
		c = new InvocationContext();
		c.actionService = actionService;
		c.action = action;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void invoke() {
		
		c.actionService.resourseService.checkHandlerType(c.action);
		
		List<Filter> filters = c.actionService.resourseService.getFilters(c.action);
		List<Interceptor<?>> interceptors = c.actionService.resourseService.getInterceptors(c.action);
		Handler handler = c.actionService.resourseService.getHandler(c.action);

		c.filters = filters;
		c.interceptors = (List)interceptors;
		c.handler = handler;
		
		FiltersBlock block = new FiltersBlock(c);
		block.invoke();
	}

}
