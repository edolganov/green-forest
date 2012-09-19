package com.gf.core.engine.filter.model;

import com.gf.Action;
import com.gf.Filter;
import com.gf.annotation.Order;
import com.gf.service.FilterChain;

@Order(Integer.MAX_VALUE)
public class EndFilter extends Filter {

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		chain.doNext();
	}

}
