package com.gf.core.action;

import com.gf.Action;
import com.gf.core.context.StaticContext;
import com.gf.core.deploy.ResourseService;
import com.gf.core.util.CoreUtil;
import com.gf.exception.ExceptionWrapper;
import com.gf.exception.invoke.InvocationException;
import com.gf.exception.invoke.NullActionException;
import com.gf.service.ActionService;
import com.gf.service.ConfigService;
import com.gf.util.Util;

@SuppressWarnings("unchecked")
public class ActionServiceImpl implements ActionService {
	
	public ResourseService resourse;
	public ConfigService config;
	public StaticContext staticContext;
	public Object owner;
	
	public ActionServiceImpl(Object owner, ConfigService config, ResourseService resourseService, StaticContext staticContext) {
		this.owner = owner;
		this.resourse = resourseService;
		this.config = config;
		this.staticContext = staticContext;
	}
	

	@Override
	public <I, O> O invoke(Action<I, O> action) throws InvocationException, ExceptionWrapper, RuntimeException {
		
		try {
			
			return (O)invokeUnwrap(action);
			
		} catch (Exception e) {
			throw CoreUtil.convertException(e, "can't invoke "+action);
		}

	}


	@Override
	public <I, O> O invokeUnwrap(Action<I, O> action) throws InvocationException, Exception {
		
		if(Util.isEmpty(action)){
			throw new NullActionException();
		}
		
		InvocationBlock block = new InvocationBlock(this, action);
		block.invoke();
		
		Object out = action.getOutput();
		return (O)out;
	}

}
