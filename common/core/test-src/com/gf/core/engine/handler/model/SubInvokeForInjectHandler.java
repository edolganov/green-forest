package com.gf.core.engine.handler.model;

import com.gf.Handler;
import com.gf.annotation.Mapping;
import com.gf.test.action.EmptyAction;

@Mapping(SubInvokeAction.class)
public class SubInvokeForInjectHandler extends Handler<SubInvokeAction>{

	@Override
	public void invoke(SubInvokeAction action) throws Exception {
		
		subInvoke(new EmptyAction());
		
	}

}
