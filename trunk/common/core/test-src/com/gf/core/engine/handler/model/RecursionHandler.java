package com.gf.core.engine.handler.model;

import com.gf.Handler;
import com.gf.annotation.Mapping;

@Mapping(RecursionAction.class)
public class RecursionHandler extends Handler<RecursionAction>{

	@Override
	public void invoke(RecursionAction action) throws Exception {
		
		subInvoke(new SubInvokeAction());
		subInvoke(new RecursionAction());
		
	}

}
