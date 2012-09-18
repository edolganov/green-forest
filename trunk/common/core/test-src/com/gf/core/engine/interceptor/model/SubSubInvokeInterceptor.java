package com.gf.core.engine.interceptor.model;

import com.gf.Interceptor;
import com.gf.InterceptorChain;
import com.gf.annotation.Mapping;
import com.gf.annotation.Order;
import com.gf.core.engine.handler.model.SubInvokeAction;
import com.gf.core.engine.handler.model.SubSubInvokeAction;

@Order(Integer.MAX_VALUE)
@Mapping(SubSubInvokeAction.class)
public class SubSubInvokeInterceptor extends Interceptor<SubSubInvokeAction>{

	@Override
	public void invoke(SubSubInvokeAction action, InterceptorChain chain)
			throws Exception {
		
		subInvoke(new SubInvokeAction());
		subInvoke(new SubInvokeAction());
		
	}

}
