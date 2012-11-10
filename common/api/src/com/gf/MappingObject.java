package com.gf;

import com.gf.service.InvocationService;

public abstract class MappingObject extends InvocationObject {
	
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
