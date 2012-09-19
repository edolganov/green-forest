package com.gf.core.engine.interceptor.model;

import com.gf.Interceptor;
import com.gf.annotation.Mapping;
import com.gf.annotation.Order;
import com.gf.service.InterceptorChain;
import com.gf.test.action.StringAction;

@Order(1)
@Mapping(StringAction.class)
public class FirstByAnn extends Interceptor<StringAction> {

	@Override
	public void invoke(StringAction action, InterceptorChain chain) throws Exception {
		
		String input = action.getInput();
		action.setInput(input+"-first");
		
		chain.doNext();
	}

}
