package com.green.forest.core.action.interceptor;

import com.green.forest.core.action.InvocationContext;

public class InterceptorsBlock {
	
	InvocationContext c;

	public InterceptorsBlock(InvocationContext context) {
		this.c = context;
	}

	public void invoke() {
		
		InterceptorChainImpl chain = new InterceptorChainImpl(c);
		chain.invoke();
		
	}



}
