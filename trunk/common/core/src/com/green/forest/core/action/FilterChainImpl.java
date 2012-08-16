package com.green.forest.core.action;

import java.util.List;

import com.green.forest.api.Filter;
import com.green.forest.api.FilterChain;

public class FilterChainImpl {
	
	FilterBlock owner;
	private List<Filter> filters;

	public FilterChainImpl(FilterBlock owner, List<Filter> filters) {
		this.owner = owner;
		this.filters = filters;
	}

	public void invoke() {
		int firstIndex = 0;
		if(hasFilter(firstIndex)){
			FilterChainItem first = new FilterChainItem(this, firstIndex);
			first.invoke();
		}
	}
	
	boolean hasFilter(int index){
		return index < filters.size();
	}
	
	Filter getFilter(int index){
		return filters.get(index);
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
		Filter filter = owner.getFilter(index);
		filter.invoke(this);
	}

	@Override
	public void nextFilter() {
		int nextIndex = index++;
		if( owner.hasFilter(nextIndex)){
			FilterChainItem next = new FilterChainItem(owner, nextIndex);
			next.invoke();
		}
	}
	
}
