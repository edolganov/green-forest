package com.gf.core.engine.filter.model;

import com.gf.Action;
import com.gf.Filter;
import com.gf.core.engine.model.OtherServiceImpl;
import com.gf.service.FilterChain;

public class InvocationContextSetterFilter extends Filter {

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		invocationContext.addToInvocationContext(new OtherServiceImpl());
		
		chain.doNext();
		
	}

}
