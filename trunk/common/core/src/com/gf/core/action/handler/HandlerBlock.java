package com.gf.core.action.handler;

import com.gf.core.action.InvocationContext;

@SuppressWarnings({ "unchecked"})
public class HandlerBlock {
	
	InvocationContext c;
	
	public HandlerBlock(InvocationContext context) {
		this.c = context;
	}
	
	public void invoke() throws Exception {
		
		c.initMappingObject(c.handler);
		c.handler.invoke(c.action);
	}


}
