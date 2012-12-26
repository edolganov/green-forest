package com.gf.core.engine.interceptor.model.mapping;

import com.gf.Action;
import com.gf.Interceptor;
import com.gf.annotation.Mapping;
import com.gf.service.InterceptorChain;
import com.gf.util.junit.AssertExt;

@Mapping(SomeInterface.class)
public class SomeInterceptor extends Interceptor<Action<?,?>> {

	@Override
	public void invoke(Action<?, ?> action, InterceptorChain chain)
			throws Exception {
		
		AssertExt.assertTrue(action instanceof SomeInterface);
		
		chain.doNext();
		
	}

}
