package com.gf.core.engine.handler.model;

import com.gf.Handler;
import com.gf.annotation.Mapping;
import com.gf.test.action.StringAction;

@Mapping(SubInvokeAction.class)
public class SubInvokeHandler extends Handler<SubInvokeAction>{

	@Override
	public void invoke(SubInvokeAction action) throws Exception {
		
		subInvoke(new StringAction("test"));
		
	}

}