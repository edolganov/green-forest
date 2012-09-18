package com.gf.core.action.handler;

import com.gf.core.action.InvocationContext;
import com.gf.exception.BaseException;
import com.gf.exception.ExternalException;

@SuppressWarnings({ "unchecked"})
public class HandlerBlock {
	
	InvocationContext c;
	
	public HandlerBlock(InvocationContext context) {
		this.c = context;
	}
	
	public void invoke() {
		
		c.initMappingObject(c.handler);
		
		try {
			c.handler.invoke(c.action);
		} catch (BaseException e) {
			throw e;
		} catch (Exception e) {
			throw new ExternalException("can't invoke "+c.action+" by "+c.handler, e);
		}
	}


}
