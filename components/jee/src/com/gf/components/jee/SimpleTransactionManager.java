package com.gf.components.jee;

import javax.ejb.SessionContext;
import javax.transaction.UserTransaction;

import com.gf.Action;
import com.gf.annotation.Inject;
import com.gf.annotation.Order;
import com.gf.extra.invocation.reader.InvocationReaderFilter;
import com.gf.service.FilterChain;

@Order(Order.SYSTEM_ORDER)
public class SimpleTransactionManager extends InvocationReaderFilter {
	
	@Inject
	SessionContext sessionContext;

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		UserTransaction utx = sessionContext.getUserTransaction();
		try{
			
			utx.begin();
			
			chain.doNext();
			
			utx.commit();
			
		}catch (Exception e) {
			utx.rollback();
			throw e;
		}

		
	}

}
