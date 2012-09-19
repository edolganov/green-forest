package com.gf.core.engine.filter.model;

import junit.framework.Assert;

import com.gf.Action;
import com.gf.Filter;
import com.gf.annotation.Inject;
import com.gf.core.engine.model.StaticService;
import com.gf.service.FilterChain;

public class StaticInjectFilter extends Filter {
	
	@Inject
	private StaticService staticService;

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		Assert.assertNotNull(staticService);
		
	}

}
