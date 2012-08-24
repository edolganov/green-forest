package com.green.forest.core.action;

import java.util.List;

import com.green.forest.api.Action;
import com.green.forest.api.Filter;
import com.green.forest.core.deploy.ResourseService;


public class FilterBlock {
	
	InvocationBlock owner;
	ResourseService resourseService;
	
	public FilterBlock(InvocationBlock owner) {
		
		this.owner = owner;
		resourseService = owner.resourseService;
	}
	
	public void invoke(Action<?,?> action) {
		
		List<Filter> filters = resourseService.getFilters(action);
		FilterChainImpl chain = new FilterChainImpl(this, filters);
		chain.invoke();
		
	}

}
