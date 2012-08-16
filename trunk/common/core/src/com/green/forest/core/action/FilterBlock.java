package com.green.forest.core.action;

import java.util.List;
import java.util.Set;

import com.green.forest.api.Action;
import com.green.forest.api.Filter;


public class FilterBlock {
	
	InvocationBlock owner;
	ActionServiceImpl service;
	Set<Class<?>> filterTypes;
	
	public FilterBlock(InvocationBlock owner) {
		
		this.owner = owner;
		
		service = owner.owner;
		filterTypes = service.filterTypes;
	}
	
	public void invoke(Action<?,?> action) {
		
		List<Filter> filters = getFilters(action);
		FilterChainImpl chain = new FilterChainImpl(this, filters);
		chain.invoke();
		
	}

	private List<Filter> getFilters(Action<?,?> action) {
		// TODO Auto-generated method stub
		return null;
	}

}
