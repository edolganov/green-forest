package com.gf.core.engine.interceptor.model;

import junit.framework.Assert;

import com.gf.Interceptor;
import com.gf.InterceptorChain;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;
import com.gf.test.action.EmptyAction;

@Mapping(EmptyAction.class)
public class InjectUnknownInterceptor extends Interceptor<EmptyAction> {
	
	@Inject
	private UnknownInjectClass unknown;

	@Override
	public void invoke(EmptyAction action, InterceptorChain chain) throws Exception {
		Assert.fail("ex exp");
	}

}

class UnknownInjectClass {
	
}
