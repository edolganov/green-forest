package com.green.forest.core.action;

import com.green.forest.api.Action;
import com.green.forest.core.context.InvocationContext;
import com.green.forest.core.deploy.ResourseService;


public class InvocationBlock {
	
	ActionServiceImpl owner;
	ResourseService resourseService;
	InvocationContext context = new InvocationContext();
	
	public InvocationBlock(ActionServiceImpl owner){
		this.owner = owner;
		resourseService = owner.resourseService;
	}
	
	public Object invoke(Action<?,?> action) {
		
		resourseService.checkHandlerType(action);
		
		context.addAll(owner.staticContext);
		
		invokeFilterBlock(action);
		Object out = invokeMappingBlock(action);
		return out;
	}
	
	private void invokeFilterBlock(Action<?,?> action){
		FilterBlock block = new FilterBlock(this);
		block.invoke(action);
	}
	

	private Object invokeMappingBlock(Action<?,?> action) {
		MappingBlock block = new MappingBlock(this);
		Object out = block.invoke(action);
		return out;
	}

}
