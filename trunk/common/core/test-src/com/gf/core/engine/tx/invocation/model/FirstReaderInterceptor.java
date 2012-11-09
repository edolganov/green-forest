package com.gf.core.engine.tx.invocation.model;

import com.gf.Action;
import com.gf.annotation.Order;
import com.gf.extra.invocation.reader.InvocationReaderInterceptor;
import com.gf.service.InterceptorChain;

@Order(1)
public class FirstReaderInterceptor extends InvocationReaderInterceptor<Action<?,?>>{

	@Override
	public void invoke(Action<?, ?> action, InterceptorChain chain)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
