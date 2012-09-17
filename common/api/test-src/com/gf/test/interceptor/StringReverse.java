package com.gf.test.interceptor;

import com.gf.Interceptor;
import com.gf.InterceptorChain;
import com.gf.annotation.Mapping;
import com.gf.test.action.StringAction;
import com.gf.util.Util;

@Mapping(StringAction.class)
public class StringReverse extends Interceptor<StringAction>{

	@Override
	public void invoke(StringAction action, InterceptorChain chain) throws Exception {
		
		String input = action.getInput();
		if( ! Util.isEmpty(input)){
			String newInput = new StringBuilder(input).reverse().toString();
			action.setInput(newInput);
		}
		
		chain.doNext();
		
	}

}
