package com.gf.core.engine.filter.model;

import junit.framework.Assert;

import com.gf.Action;
import com.gf.Filter;
import com.gf.annotation.Inject;
import com.gf.annotation.Order;
import com.gf.core.engine.model.OtherService;
import com.gf.core.engine.model.StaticService;
import com.gf.service.FilterChain;

@Order(Integer.MAX_VALUE)
public class InjectAllFilter extends Filter {
	
	@Inject
	private StaticService staticService;
	
	@Inject
	private OtherService otherService;

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		Assert.assertNotNull(staticService);
		Assert.assertNotNull(otherService);
		
	}

}
