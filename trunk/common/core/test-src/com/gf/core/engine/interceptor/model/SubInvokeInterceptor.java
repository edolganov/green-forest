package com.gf.core.engine.interceptor.model;

import com.gf.Interceptor;
import com.gf.InterceptorChain;
import com.gf.annotation.Mapping;
import com.gf.annotation.Order;
import com.gf.core.engine.handler.model.SubInvokeAction;
import com.gf.test.action.StringAction;

@Order(Integer.MAX_VALUE)
@Mapping(SubInvokeAction.class)
public class SubInvokeInterceptor extends Interceptor<SubInvokeAction>{

	@Override
	public void invoke(SubInvokeAction action, InterceptorChain chain)
			throws Exception {
		
		subInvoke(new StringAction("test"));
		
		
	}

}
