package com.gf.core.engine.interceptor.model;

import com.gf.Interceptor;
import com.gf.InterceptorChain;
import com.gf.annotation.Mapping;
import com.gf.core.engine.handler.model.RecursionAction;
import com.gf.core.engine.handler.model.SubInvokeAction;

@Mapping(RecursionAction.class)
public class RecursionInterveptor extends Interceptor<RecursionAction>{


	@Override
	public void invoke(RecursionAction action, InterceptorChain chain)
			throws Exception {
		subInvoke(new SubInvokeAction());
		subInvoke(new RecursionAction());
	}

}
