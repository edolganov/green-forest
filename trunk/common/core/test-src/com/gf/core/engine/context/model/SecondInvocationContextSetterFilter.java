package com.gf.core.engine.context.model;

import com.gf.Action;
import com.gf.Filter;
import com.gf.annotation.Order;
import com.gf.core.engine.model.InvocationServiceImpl;
import com.gf.core.engine.model.InvocationServiceImpl2;
import com.gf.service.FilterChain;

@Order(Integer.MIN_VALUE)
public class SecondInvocationContextSetterFilter extends Filter {
	

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		addToInvocationContext(new InvocationServiceImpl());
		addToInvocationContext(new InvocationServiceImpl2());
		
		chain.doNext();
		
	}

}
