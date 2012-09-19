package com.gf.core.engine.handler.model;

import junit.framework.Assert;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;
import com.gf.test.action.EmptyAction;

@Mapping(EmptyAction.class)
public class InjectUnknownHandler extends Handler<EmptyAction> {
	
	@Inject
	private UnknownInjectClass unknown;

	@Override
	public void invoke(EmptyAction action) throws Exception {
		
		Assert.fail("ex exp");
		
		
	}

}

class UnknownInjectClass {
	
}
