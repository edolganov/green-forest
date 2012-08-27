package com.green.forest.api.test.interceptor;

import com.green.forest.api.Interceptor;
import com.green.forest.api.InterceptorChain;
import com.green.forest.api.annotation.Mapping;
import com.green.forest.api.test.action.StringAction;
import com.green.forest.util.Util;

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
