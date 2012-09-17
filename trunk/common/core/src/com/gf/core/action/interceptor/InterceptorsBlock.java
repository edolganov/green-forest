package com.gf.core.action.interceptor;

import com.gf.core.action.InvocationContext;

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
