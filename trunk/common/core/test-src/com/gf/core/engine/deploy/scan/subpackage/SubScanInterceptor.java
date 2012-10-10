package com.gf.core.engine.deploy.scan.subpackage;

import com.gf.Action;
import com.gf.Interceptor;
import com.gf.annotation.Mapping;
import com.gf.annotation.Order;
import com.gf.service.InterceptorChain;

@Order(Integer.MIN_VALUE)
@Mapping(Action.class)
public class SubScanInterceptor extends Interceptor<Action<?,?>>{

	@Override
	public void invoke(Action<?, ?> action, InterceptorChain chain)
			throws Exception {
		
		chain.doNext();
		
	}

}
