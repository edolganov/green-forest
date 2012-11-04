package com.gf.core.engine.tracing.model;

import com.gf.Action;
import com.gf.Filter;
import com.gf.annotation.Inject;
import com.gf.core.Engine;
import com.gf.service.FilterChain;

public class CallSubEngineWithSameActionFilter extends Filter{
	
	@Inject
	Engine engine;


	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		engine.invoke(action);
		chain.doNext();
	}

}
