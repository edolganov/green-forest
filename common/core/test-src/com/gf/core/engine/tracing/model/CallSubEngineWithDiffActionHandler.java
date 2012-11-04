package com.gf.core.engine.tracing.model;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;
import com.gf.core.Engine;
import com.gf.test.action.EmptyAction;

@Mapping(EmptyAction.class)
public class CallSubEngineWithDiffActionHandler extends Handler<EmptyAction>{
	
	@Inject
	Engine engine;

	@Override
	public void invoke(EmptyAction action) throws Exception {
		
		engine.invoke(new EmptyAction());
		
	}

}
