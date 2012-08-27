package com.green.forest.core.engine.interceptor.model;

import com.green.forest.api.Action;
import com.green.forest.api.Interceptor;
import com.green.forest.api.InterceptorChain;
import com.green.forest.api.annotation.Mapping;
import com.green.forest.api.annotation.Order;

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
