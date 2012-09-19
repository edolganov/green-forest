package com.gf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
