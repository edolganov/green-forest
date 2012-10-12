package com.gf.components.tx;

import javax.transaction.UserTransaction;

/**
 * Manager of transactions
 */
public interface TxManager {

	UserTransaction getUserTransaction();

}
