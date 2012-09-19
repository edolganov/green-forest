package com.gf.core.engine.context.model;

import junit.framework.Assert;

import com.gf.Action;
import com.gf.Filter;
import com.gf.annotation.Inject;
import com.gf.core.engine.model.InvocationService;
import com.gf.core.engine.model.InvocationServiceImpl2;
import com.gf.service.FilterChain;

public class SecondInvocationServiceExpectedFilter extends Filter {
	
	@Inject
	InvocationService service;

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		Assert.assertEquals(InvocationServiceImpl2.class, service.getClass());
		
	}

}
