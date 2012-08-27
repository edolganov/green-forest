package com.green.forest.core.engine.interceptor.model;

import com.green.forest.api.Interceptor;
import com.green.forest.api.InterceptorChain;
import com.green.forest.api.annotation.Mapping;
import com.green.forest.api.test.action.StringAction;

@Mapping(StringAction.class)
public class First extends Interceptor<StringAction> {

	@Override
	public void invoke(StringAction action, InterceptorChain chain) throws Exception {
		
		String input = action.getInput();
		action.setInput(input+"-first");
		
		chain.doNext();
	}

}