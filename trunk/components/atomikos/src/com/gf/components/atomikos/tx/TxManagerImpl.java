package com.gf.components.atomikos.tx;

import javax.transaction.UserTransaction;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.gf.components.tx.TxManager;

/**
 * Manager of connections to a database and transactions
 */
public class TxManagerImpl implements TxManager {

	private UserTransactionManager utm;

	public void init() throws Exception {
		utm = new UserTransactionManager();
		utm.init();
	}

	@Override
	public UserTransaction getUserTransaction() {
		return new UserTransactionImp();
	}

}
