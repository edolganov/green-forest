package com.gf.core.engine.interceptor.model;

import com.gf.Interceptor;
import com.gf.InterceptorChain;
import com.gf.annotation.Mapping;
import com.gf.annotation.Order;
import com.gf.test.action.StringAction;

@Order(2)
@Mapping(StringAction.class)
public class SecondByAnn extends Interceptor<StringAction> {
	
	@Override
	public void invoke(StringAction action, InterceptorChain chain) throws Exception {
		String input = action.getInput();
		action.setInput(input+"-second");
		
		chain.doNext();
	}
	
}