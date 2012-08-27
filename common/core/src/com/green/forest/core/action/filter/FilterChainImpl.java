package com.green.forest.core.action.filter;

import com.green.forest.api.Filter;
import com.green.forest.api.FilterChain;
import com.green.forest.core.CoreUtil;
import com.green.forest.core.action.InvocationContext;
import com.green.forest.core.action.interceptor.InterceptorsBlock;

public class FilterChainImpl {
	
	InvocationContext c;

	public FilterChainImpl(InvocationContext context) {
		this.c = context;
	}

	public void invoke() {
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
	
	void doInterceptorsBlock() {
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

	void invoke(){
		
		Filter filter = owner.getItem(index);
		
		owner.c.addToTrace(filter);
		
		try {
			filter.invoke(owner.c.action, this);
		}catch (Exception e) {
			throw CoreUtil.convertException(e, "can't invoke "+owner.c.action+" by "+filter);
		}
	}

	@Override
	public void doNext() {
		int nextIndex = index + 1;
		if( owner.hasItem(nextIndex)){
			FilterChainItem next = new FilterChainItem(owner, nextIndex);
			next.invoke();
		} else {
			owner.doInterceptorsBlock();
		}
	}
	
}
