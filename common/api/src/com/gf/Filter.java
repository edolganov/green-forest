package com.gf;

import com.gf.log.Log;
import com.gf.log.LogFactory;
import com.gf.service.FilterChain;
import com.gf.service.InvocationContextService;

public abstract class Filter {
	
	protected Log log = LogFactory.getLog(getClass());
	protected InvocationContextService invocationContext;
	
	public abstract void invoke(Action<?,?> action, FilterChain chain) throws Exception;

	public void setInvocationContext(InvocationContextService invocationContext) {
		this.invocationContext = invocationContext;
	}

}
