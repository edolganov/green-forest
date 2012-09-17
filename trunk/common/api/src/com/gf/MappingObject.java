package com.gf;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class MappingObject {
	
	protected Log log = LogFactory.getLog(getClass());
	
	private InvocationControll invocation;
	
	protected <I extends Serializable,O extends Serializable> O subInvoke(Action<I, O> action) throws Exception {
		return (O)invocation.subInvoke(action);
	}
	
	protected InvocationControll getInvocation() {
		return invocation;
	}

	public void setInvocation(InvocationControll invocation) {
		this.invocation = invocation;
	}
	

}
