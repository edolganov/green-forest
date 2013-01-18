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
package com.gf.core.action.filter;

import com.gf.Filter;
import com.gf.core.action.InvocationContext;
import com.gf.core.action.interceptor.InterceptorsBlock;
import com.gf.core.action.trace.Body;
import com.gf.service.FilterChain;

public class FilterChainImpl {
	
	InvocationContext c;

	public FilterChainImpl(InvocationContext context) {
		this.c = context;
	}

	public void invoke() throws Exception {
		int firstIndex = 0;
		if(hasItem(firstIndex)){
			FilterChainItem first = new FilterChainItem(this, firstIndex);
			first.invoke();
		} else {
			doInterceptorsBlock();
		}
	}
	
	boolean hasItem(int index){
		return index < c.filters.size();
	}
	
	Filter getItem(int index){
		return c.filters.get(index);
	}
	
	void doInterceptorsBlock() throws Exception {
		InterceptorsBlock block = new InterceptorsBlock(c);
		block.invoke();
	}

}

class FilterChainItem implements FilterChain {
	
	FilterChainImpl owner;
	int index;

	public FilterChainItem(FilterChainImpl owner, int index) {
		super();
		this.owner = owner;
		this.index = index;
	}

	void invoke() throws Exception {
		
		final Filter filter = owner.getItem(index);
		
		owner.c.traceWrapper.wrapHandler(filter, new Body() {
			
			@Override
			public void invocation() throws Throwable {
				owner.c.initFilter(filter);
				filter.invoke(owner.c.action, FilterChainItem.this);
			}
		});

	}

	public void doNext() throws Exception {
		int nextIndex = index + 1;
		if( owner.hasItem(nextIndex)){
			FilterChainItem next = new FilterChainItem(owner, nextIndex);
			next.invoke();
		} else {
			owner.doInterceptorsBlock();
		}
	}
	
}
