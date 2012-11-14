package com.gf.core.engine.filter.model;

import junit.framework.Assert;

import com.gf.Action;
import com.gf.Filter;
import com.gf.annotation.Inject;
import com.gf.service.FilterChain;


public class InjectUnknownFilter extends Filter {
	
	@Inject
	private UnknownInjectClass unknown;

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		Assert.fail("ex exp");
	}

}

class UnknownInjectClass {
	
}