package com.gf.test.interceptor;

import com.gf.Interceptor;
import com.gf.annotation.Mapping;
import com.gf.service.InterceptorChain;
import com.gf.test.action.StringAction;
import com.gf.util.Util;

@Mapping(StringAction.class)
public class StringDuplicator extends Interceptor<StringAction>{

	@Override
	public void invoke(StringAction action, InterceptorChain chain)
			throws Exception {
		
		String input = action.getInput();
		if( ! Util.isEmpty(input)){
			String newInput = input + input;
			action.setInput(newInput);
		}
		
		chain.doNext();
	}

}
