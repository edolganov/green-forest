package com.gf.components.tx;

import javax.transaction.UserTransaction;

import com.gf.Action;
import com.gf.Filter;
import com.gf.annotation.Inject;
import com.gf.annotation.Order;
import com.gf.service.FilterChain;

@Order(UserTransactionOnInvoke.ORDER)
public class UserTransactionOnInvoke extends Filter {
	
	public static final int ORDER = -100;
	
	@Inject
	TxManager txManager;

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		UserTransaction userTx = txManager.getUserTransaction();
		userTx.begin();
		try {
			
			chain.doNext();
			userTx.commit();
			
		}catch (Exception e) {
			try {
				userTx.rollback();
			}catch (Exception e2) {
				log.error("can't rollback", e2);
			}
			throw e;
		}
	}

}
