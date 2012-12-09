package com.gf.core.engine.context.model;

import com.gf.Action;
import com.gf.Filter;
import com.gf.annotation.Order;
import com.gf.core.engine.model.StaticServiceImpl2;
import com.gf.service.FilterChain;

@Order(Integer.MIN_VALUE)
public class RewriteStaticContextFilter extends Filter {

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		addToInvocationContext(new StaticServiceImpl2());
		
		chain.doNext();
		
	}
	
	

}
