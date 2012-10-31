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

	@Override
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
