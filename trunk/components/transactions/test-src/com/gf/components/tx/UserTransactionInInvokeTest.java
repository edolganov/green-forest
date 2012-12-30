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

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.components.UserTxAndConnection;
import com.gf.test.action.EmptyAction;
import com.gf.util.junit.AssertExt;

public class UserTransactionInInvokeTest extends AssertExt {
	
	
	@Test
	public void test_tx_flag_in_action(){
		
		Engine engine = new Engine();
		engine.addToContext(new TxManagerStub());
		engine.putFilter(UserTransactionInInvoke.class);
		engine.putHandler(CheckUserTxFlagHandler.class);
		
		
		EmptyAction action = new EmptyAction();
		assertFalse(action.containsAttr(UserTxAndConnection.USER_TX_IN_INVOKE_FLAG));
		engine.invoke(action);
		assertFalse(action.containsAttr(UserTxAndConnection.USER_TX_IN_INVOKE_FLAG));
		
	}

}
