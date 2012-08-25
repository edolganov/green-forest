package com.green.forest.core.action.filter;

import com.green.forest.core.action.InvocationContext;


public class FiltersBlock {
	
	InvocationContext c;
	
	public FiltersBlock(InvocationContext context) {
		this.c = context;
	}
	
	public void invoke() {
		
		FilterChainImpl chain = new FilterChainImpl(c);
		chain.invoke();
		
	}

}
