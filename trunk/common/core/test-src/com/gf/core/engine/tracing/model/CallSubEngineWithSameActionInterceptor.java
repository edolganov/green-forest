package com.gf.core.engine.tracing.model;

import com.gf.Interceptor;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;
import com.gf.core.Engine;
import com.gf.service.InterceptorChain;
import com.gf.test.action.EmptyAction;

@Mapping(EmptyAction.class)
public class CallSubEngineWithSameActionInterceptor extends Interceptor<EmptyAction>{
	
	@Inject
	Engine engine;


	@Override
	public void invoke(EmptyAction action, InterceptorChain chain) throws Exception {
		engine.invoke(action);
		chain.doNext();
		
	}

}
