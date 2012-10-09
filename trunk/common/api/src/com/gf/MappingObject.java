package com.gf;

import com.gf.log.Log;
import com.gf.log.LogFactory;
import com.gf.service.InvocationService;

public abstract class MappingObject {
	
	protected Log log = LogFactory.getLog(getClass());
	
	private InvocationService invocation;
	
	protected <I,O> O subInvoke(Action<I, O> action) throws Exception {
		return (O)invocation.subInvoke(action);
	}
	
	protected InvocationService getInvocation() {
		return invocation;
	}

	public void setInvocation(InvocationService invocation) {
		this.invocation = invocation;
	}
	

}
