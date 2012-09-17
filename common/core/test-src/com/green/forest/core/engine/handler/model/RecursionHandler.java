package com.green.forest.core.engine.handler.model;

import com.green.forest.api.Handler;
import com.green.forest.api.annotation.Mapping;

@Mapping(RecursionAction.class)
public class RecursionHandler extends Handler<RecursionAction>{

	@Override
	public void invoke(RecursionAction action) throws Exception {
		
		subInvoke(new RecursionAction());
		
	}

}
