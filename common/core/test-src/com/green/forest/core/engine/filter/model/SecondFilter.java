package com.green.forest.core.engine.filter.model;

import com.green.forest.api.Action;
import com.green.forest.api.Filter;
import com.green.forest.api.FilterChain;
import com.green.forest.api.annotation.Order;

@Order(2)
public class SecondFilter extends Filter {

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		chain.doNext();
	}

}
