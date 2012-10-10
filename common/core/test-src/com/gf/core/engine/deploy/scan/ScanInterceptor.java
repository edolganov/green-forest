package com.gf.core.engine.deploy.scan;

import com.gf.Action;
import com.gf.Interceptor;
import com.gf.annotation.Mapping;
import com.gf.service.InterceptorChain;

@Mapping(Action.class)
public class ScanInterceptor extends Interceptor<Action<?,?>>{

	@Override
	public void invoke(Action<?, ?> action, InterceptorChain chain)
			throws Exception {
		
		chain.doNext();
		
	}

}
