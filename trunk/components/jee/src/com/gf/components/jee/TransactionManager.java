package com.gf.components.jee;

import javax.ejb.SessionContext;

import com.gf.Action;
import com.gf.annotation.Inject;
import com.gf.annotation.Order;
import com.gf.extra.invocation.reader.InvocationReaderFilter;
import com.gf.service.FilterChain;

@Order(Order.SYSTEM_ORDER)
public class TransactionManager extends InvocationReaderFilter {
	
	@Inject
	SessionContext sessionContext;

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		
		
	}

}
