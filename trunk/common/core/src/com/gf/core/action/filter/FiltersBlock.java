package com.gf.core.action.filter;

import com.gf.core.action.InvocationContext;


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
