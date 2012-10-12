package com.gf.components.tx;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;

/**
 * Manager of connections to a database and transactions
 */
public interface TxManager {

	void init() throws Exception;

	UserTransaction getUserTransaction();
	
	DataSource getDataSource();
	
	void destroy();

}
