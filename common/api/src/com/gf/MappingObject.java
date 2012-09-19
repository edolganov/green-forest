package com.gf;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gf.service.InvocationService;

public abstract class MappingObject {
	
	protected Log log = LogFactory.getLog(getClass());
	
	private InvocationService invocation;
	
	protected <I extends Serializable,O extends Serializable> O subInvoke(Action<I, O> action) throws Exception {
		return (O)invocation.subInvoke(action);
	}
	
	protected InvocationService getInvocation() {
		return invocation;
	}

	public void setInvocation(InvocationService invocation) {
		this.invocation = invocation;
	}
	

}
