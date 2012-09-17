package com.gf.core.engine.filter.model;

import com.gf.Action;
import com.gf.Filter;
import com.gf.FilterChain;
import com.gf.annotation.Order;

@Order(2)
public class SecondFilter extends Filter {

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		chain.doNext();
	}

}
