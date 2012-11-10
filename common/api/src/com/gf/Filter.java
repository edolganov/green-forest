package com.gf;

import com.gf.service.FilterChain;
import com.gf.service.InvocationContextService;

public abstract class Filter extends InvocationObject {
	
	protected InvocationContextService invocationContext;
	
	public abstract void invoke(Action<?,?> action, FilterChain chain) throws Exception;

	public void setInvocationContext(InvocationContextService invocationContext) {
		this.invocationContext = invocationContext;
	}

}
