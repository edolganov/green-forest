package com.green.forest.core.action.handler;

import com.green.forest.api.exception.BaseException;
import com.green.forest.api.exception.ExternalException;
import com.green.forest.core.action.InvocationContext;

public class HandlerBlock {
	
	InvocationContext c;
	
	public HandlerBlock(InvocationContext context) {
		this.c = context;
	}
	
	@SuppressWarnings("unchecked")
	public void invoke() {
		
		try {
			c.handler.invoke(c.action);
		} catch (BaseException e) {
			throw e;
		} catch (Exception e) {
			throw new ExternalException("can't invoke "+c.action+" by "+c.handler, e);
		}
	}

}
