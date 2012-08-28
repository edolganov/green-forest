package com.green.forest.core.action.handler;

import com.green.forest.api.exception.BaseException;
import com.green.forest.api.exception.ExternalException;
import com.green.forest.core.action.InvocationContext;

@SuppressWarnings({ "unchecked"})
public class HandlerBlock {
	
	InvocationContext c;
	
	public HandlerBlock(InvocationContext context) {
		this.c = context;
	}
	
	public void invoke() {
		
		c.init(c.handler);
		
		try {
			c.handler.invoke(c.action);
		} catch (BaseException e) {
			throw e;
		} catch (Exception e) {
			throw new ExternalException("can't invoke "+c.action+" by "+c.handler, e);
		}
	}


}
