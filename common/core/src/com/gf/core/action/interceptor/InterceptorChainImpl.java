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
package com.gf.core.action.interceptor;

import com.gf.Interceptor;
import com.gf.core.action.InvocationContext;
import com.gf.core.action.handler.HandlerBlock;
import com.gf.core.action.trace.Body;
import com.gf.service.InterceptorChain;

public class InterceptorChainImpl {
	
	InvocationContext c;

	public InterceptorChainImpl(InvocationContext context) {
		this.c = context;
	}

	public void invoke() throws Exception {
		int firstIndex = 0;
		if(hasItem(firstIndex)){
			InterceptorChainItem first = new InterceptorChainItem(this, firstIndex);
			first.invoke();
		} else {
			doHandlerBlock();
		}
		
	}
	
	boolean hasItem(int index){
		return index < c.interceptors.size();
	}
	
	Interceptor<?> getItem(int index){
		return c.interceptors.get(index);
	}
	
	void doHandlerBlock() throws Exception {
		HandlerBlock block = new HandlerBlock(c);
		block.invoke();
	}

}

@SuppressWarnings({ "rawtypes", "unchecked" })
class InterceptorChainItem implements InterceptorChain {
	
	InterceptorChainImpl owner;
	int index;

	public InterceptorChainItem(InterceptorChainImpl owner, int index) {
		super();
		this.owner = owner;
		this.index = index;
	}
	
	void invoke() throws Exception {
		
		final Interceptor interceptor = owner.getItem(index);
		
		owner.c.traceWrapper.wrapHandler(interceptor, new Body() {
			
			@Override
			public void invocation() throws Throwable {
				owner.c.initMappingObject(interceptor);
				interceptor.invoke(owner.c.action, InterceptorChainItem.this);
			}
		});

	}

	@Override
	public void doNext() throws Exception {
		int nextIndex = index + 1;
		if( owner.hasItem(nextIndex)){
			InterceptorChainItem next = new InterceptorChainItem(owner, nextIndex);
			next.invoke();
		} else {
			owner.doHandlerBlock();
		}
	}
	
}
