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
package com.gf.components.tx;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public class UserTxStub implements UserTransaction{

	public void begin() throws NotSupportedException, SystemException {
		
	}

	public void commit() throws HeuristicMixedException,
			HeuristicRollbackException, IllegalStateException,
			RollbackException, SecurityException, SystemException {
		
	}

	public int getStatus() throws SystemException {
		return 0;
	}

	public void rollback() throws IllegalStateException, SecurityException,
			SystemException {
		
	}

	public void setRollbackOnly() throws IllegalStateException, SystemException {
		
	}

	public void setTransactionTimeout(int arg0) throws SystemException {
		
	}

}
