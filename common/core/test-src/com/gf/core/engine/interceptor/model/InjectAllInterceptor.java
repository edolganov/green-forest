package com.gf.core.engine.interceptor.model;

import junit.framework.Assert;

import com.gf.Interceptor;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;
import com.gf.core.engine.model.InvocationService;
import com.gf.core.engine.model.StaticService;
import com.gf.service.InterceptorChain;
import com.gf.test.action.EmptyAction;

@Mapping(EmptyAction.class)
public class InjectAllInterceptor extends Interceptor<EmptyAction> {
	
	@Inject
	private StaticService staticService;
	
	@Inject
	private InvocationService otherService;

	@Override
	public void invoke(EmptyAction action, InterceptorChain chain)
			throws Exception {
		
		Assert.assertNotNull(staticService);
		Assert.assertNotNull(otherService);
		
	}

}
