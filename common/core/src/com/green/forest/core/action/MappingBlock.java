package com.green.forest.core.action;

import com.green.forest.api.Action;
import com.green.forest.api.Handler;
import com.green.forest.api.exception.BaseException;
import com.green.forest.api.exception.UnexpectedException;
import com.green.forest.core.context.InvocationContext;
import com.green.forest.core.deploy.ResourseService;

public class MappingBlock {
	
	InvocationContext context;
	Class<?> handlerType;
	ResourseService resourseService;

	public MappingBlock(InvocationBlock owner) {
		context = owner.context;
		resourseService = owner.resourseService;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object invoke(Action<?, ?> action) {
		
		Handler handler = resourseService.getHandler(action);
		
		Object out = null;
		
		try {
			handler.invoke(action);
			out = action.getOutput();
		} catch (BaseException e) {
			throw e;
		} catch (Exception e) {
			throw new UnexpectedException("can't invoke "+action+" by "+handler, e);
		}
		
		return out;
	}

}
