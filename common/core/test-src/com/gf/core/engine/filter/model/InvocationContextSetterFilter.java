package com.gf.core.engine.filter.model;

import com.gf.Action;
import com.gf.Filter;
import com.gf.annotation.Order;
import com.gf.core.engine.model.InvocationServiceImpl;
import com.gf.service.FilterChain;

@Order(Integer.MAX_VALUE)
public class InvocationContextSetterFilter extends Filter {

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		invocationContext.addToInvocationContext(new InvocationServiceImpl());
		
		chain.doNext();
		
	}

}
