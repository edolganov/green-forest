package com.green.forest.core.action.interceptor;

import com.green.forest.api.Interceptor;
import com.green.forest.api.InterceptorChain;
import com.green.forest.core.CoreUtil;
import com.green.forest.core.action.InvocationContext;
import com.green.forest.core.action.handler.HandlerBlock;

public class InterceptorChainImpl {
	
	InvocationContext c;

	public InterceptorChainImpl(InvocationContext context) {
		this.c = context;
	}

	public void invoke() {
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
	
	void doHandlerBlock(){
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
	
	void invoke(){
		
		Interceptor interceptor = owner.getItem(index);
		
		owner.c.init(interceptor);
		
		try {
			interceptor.invoke(owner.c.action, this);
		}catch (Exception e) {
			throw CoreUtil.convertException(e, "can't invoke "+owner.c.action+" by "+interceptor);
		}
	}

	@Override
	public void doNext() {
		int nextIndex = index + 1;
		if( owner.hasItem(nextIndex)){
			InterceptorChainItem next = new InterceptorChainItem(owner, nextIndex);
			next.invoke();
		} else {
			owner.doHandlerBlock();
		}
	}
	
}
