/*
 * Copyright 2012 Evgeny Dolganov (evgenij.dolganov@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gf.components.atomikos.tx;

import javax.transaction.UserTransaction;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.gf.components.tx.TxManager;

/**
 * Manager of transactions
 */
public class TxManagerImpl implements TxManager {

	private UserTransactionManager utm;

	public void init() throws Exception {
		utm = new UserTransactionManager();
		utm.init();
	}
	
	public void close(){
		utm.close();
	}

	@Override
	public UserTransaction getUserTransaction() {
		return new UserTransactionImp();
	}

}
