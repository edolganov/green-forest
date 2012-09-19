package com.gf.core.engine.context.model;

import junit.framework.Assert;

import com.gf.Action;
import com.gf.Filter;
import com.gf.annotation.Inject;
import com.gf.core.engine.model.StaticService;
import com.gf.core.engine.model.StaticServiceImpl;
import com.gf.service.FilterChain;

public class FirstStaticServiceExpectedFilter extends Filter {
	
	@Inject
	StaticService service;

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		Assert.assertEquals(StaticServiceImpl.class, service.getClass());
		
	}

}
