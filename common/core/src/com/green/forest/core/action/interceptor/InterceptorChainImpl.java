package com.green.forest.core.action.interceptor;

import com.green.forest.api.Interceptor;
import com.green.forest.api.InterceptorChain;
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

class InterceptorChainItem implements InterceptorChain {

	@Override
	public void doNext() {
		// TODO Auto-generated method stub
		
	}
	
}
