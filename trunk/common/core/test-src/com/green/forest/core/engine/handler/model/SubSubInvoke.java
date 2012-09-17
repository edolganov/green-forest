package com.green.forest.core.engine.handler.model;

import com.green.forest.api.Handler;
import com.green.forest.api.annotation.Mapping;

@Mapping(SubSubInvokeAction.class)
public class SubSubInvoke extends Handler<SubSubInvokeAction>{

	@Override
	public void invoke(SubSubInvokeAction action) throws Exception {
		
		subInvoke(new SubInvokeAction());
		subInvoke(new SubInvokeAction());
		
	}

}
