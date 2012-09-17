package com.gf.core.engine.handler.model;

import com.gf.Handler;
import com.gf.annotation.Mapping;

@Mapping(SubSubInvokeAction.class)
public class SubSubInvoke extends Handler<SubSubInvokeAction>{

	@Override
	public void invoke(SubSubInvokeAction action) throws Exception {
		
		subInvoke(new SubInvokeAction());
		subInvoke(new SubInvokeAction());
		
	}

}
