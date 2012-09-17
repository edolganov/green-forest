package com.green.forest.core.engine.handler.model;

import com.green.forest.api.Handler;
import com.green.forest.api.annotation.Mapping;
import com.green.forest.api.test.action.StringAction;

@Mapping(SubInvokeAction.class)
public class SubInvoke extends Handler<SubInvokeAction>{

	@Override
	public void invoke(SubInvokeAction action) throws Exception {
		
		subInvoke(new StringAction("test"));
		
	}

}
