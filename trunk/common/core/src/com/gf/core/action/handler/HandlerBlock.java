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
package com.gf.core.action.handler;

import com.gf.core.action.InvocationContext;
import com.gf.core.action.trace.Body;

@SuppressWarnings({ "unchecked"})
public class HandlerBlock {
	
	InvocationContext c;
	
	public HandlerBlock(InvocationContext context) {
		this.c = context;
	}
	
	public void invoke() throws Exception {
		
		c.traceWrapper.wrapHandler(c.handler, new Body() {
			
			@Override
			public void invocation() throws Throwable {
				c.initMappingObject(c.handler);
				c.handler.invoke(c.action);
			}
		});

	}


}
