package com.gf.core.engine.tracing.model;

import com.gf.Action;
import com.gf.Filter;
import com.gf.annotation.Inject;
import com.gf.core.Engine;
import com.gf.service.FilterChain;
import com.gf.test.action.EmptyAction;

public class CallSubEngineWithDiffActionFilter extends Filter{
	
	@Inject
	Engine engine;


	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		engine.invoke(new EmptyAction());
		chain.doNext();
	}

}
