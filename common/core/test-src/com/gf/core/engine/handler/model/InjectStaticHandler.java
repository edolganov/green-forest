package com.gf.core.engine.handler.model;

import junit.framework.Assert;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;
import com.gf.core.engine.model.StaticService;
import com.gf.test.action.EmptyAction;

@Mapping(EmptyAction.class)
public class InjectStaticHandler extends Handler<EmptyAction> {
	
	@Inject
	private StaticService staticService;

	@Override
	public void invoke(EmptyAction action) throws Exception {
		
		Assert.assertNotNull(staticService);
		
		
	}

}