package com.gf.core.engine.tx.invocation.model;

import com.gf.Action;
import com.gf.annotation.Order;
import com.gf.extra.invocation.reader.InvocationReaderFilter;
import com.gf.service.FilterChain;

@Order(2)
public class SecondReaderFilter extends InvocationReaderFilter {

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
