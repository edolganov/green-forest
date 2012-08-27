package com.green.forest.core.engine.filter.model;

import com.green.forest.api.Action;
import com.green.forest.api.Filter;
import com.green.forest.api.FilterChain;

public class UnorderedFilter extends Filter {

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		chain.doNext();
	}

}
