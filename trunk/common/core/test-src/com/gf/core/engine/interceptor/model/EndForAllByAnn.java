package com.gf.core.engine.interceptor.model;

import com.gf.Action;
import com.gf.Interceptor;
import com.gf.annotation.Mapping;
import com.gf.annotation.Order;
import com.gf.service.InterceptorChain;

@Order(Integer.MAX_VALUE)
@Mapping(Action.class)
public class EndForAllByAnn extends Interceptor<Action<?,?>>{

	@Override
	public void invoke(Action<?, ?> action, InterceptorChain chain)
			throws Exception {
		
		//do nothing
		
		chain.doNext();
		
	}

}
