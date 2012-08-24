package com.green.forest.core.action;

import java.io.Serializable;

import com.green.forest.api.Action;
import com.green.forest.api.ActionService;
import com.green.forest.api.exception.BaseException;
import com.green.forest.api.exception.UnexpectedException;
import com.green.forest.api.exception.invoke.NullActionException;
import com.green.forest.core.context.StaticContext;
import com.green.forest.core.deploy.ResourseService;
import com.green.forest.util.Util;

public class ActionServiceImpl implements ActionService {
	
	StaticContext staticContext = new StaticContext();
	ResourseService resourseService;
	
	public ActionServiceImpl(ResourseService resourseService) {
		this.resourseService = resourseService;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public <I extends Serializable, O extends Serializable> O invoke(Action<I, O> action) {
		
		try {
			
			if(Util.isEmpty(action)){
				throw new NullActionException();
			}
			
			InvocationBlock block = new InvocationBlock(this);
			Object out = block.invoke(action);
			return (O)out;
			
		} catch (BaseException e) {
			throw e;
		} catch (Exception e) {
			throw new UnexpectedException(e);
		}

	}

}
