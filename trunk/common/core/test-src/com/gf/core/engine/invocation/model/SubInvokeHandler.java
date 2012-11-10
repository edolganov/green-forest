package com.gf.core.engine.invocation.model;

import com.gf.Handler;
import com.gf.annotation.Mapping;
import com.gf.test.action.EmptyAction;
import com.gf.test.action.StringAction;

@Mapping(EmptyAction.class)
public class SubInvokeHandler extends Handler<EmptyAction>{

	@Override
	public void invoke(EmptyAction action) throws Exception {
		subInvoke(new StringAction());
	}

}
