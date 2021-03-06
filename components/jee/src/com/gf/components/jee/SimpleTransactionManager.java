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
