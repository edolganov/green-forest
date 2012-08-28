package com.green.forest.core.engine.handler.model;

import com.green.forest.api.Handler;
import com.green.forest.api.annotation.Mapping;
import com.green.forest.api.test.action.EmptyAction;
import com.green.forest.api.test.action.StringAction;

@Mapping(EmptyAction.class)
public class SubInvoke extends Handler<EmptyAction>{

	@Override
	public void invoke(EmptyAction action) throws Exception {
		
		subInvoke(new StringAction("test"));
		
	}

}
