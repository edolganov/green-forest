package com.gf.core.action.handler;

import com.gf.core.action.InvocationContext;
import com.gf.core.action.trace.Body;

@SuppressWarnings({ "unchecked"})
public class HandlerBlock {
	
	InvocationContext c;
	
	public HandlerBlock(InvocationContext context) {
		this.c = context;
	}
	
	public void invoke() throws Exception {
		
		c.traceWrapper.wrapHandler(c.handler, new Body() {
			
			@Override
			public void invocation() throws Throwable {
				c.initMappingObject(c.handler);
				c.handler.invoke(c.action);
			}
		});

	}


}
