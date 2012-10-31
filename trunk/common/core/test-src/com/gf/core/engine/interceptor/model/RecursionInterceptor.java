package com.gf.core.engine.interceptor.model;

import com.gf.Interceptor;
import com.gf.annotation.Mapping;
import com.gf.core.engine.handler.model.RecursionAction;
import com.gf.core.engine.handler.model.SubInvokeAction;
import com.gf.service.InterceptorChain;

@Mapping(RecursionAction.class)
public class RecursionInterceptor extends Interceptor<RecursionAction>{


	@Override
	public void invoke(RecursionAction action, InterceptorChain chain)
			throws Exception {
		subInvoke(new SubInvokeAction());
		subInvoke(new RecursionAction());
	}

}
