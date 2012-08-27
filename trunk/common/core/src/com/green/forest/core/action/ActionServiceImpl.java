package com.green.forest.core.action;

import java.io.Serializable;

import com.green.forest.api.Action;
import com.green.forest.api.ActionService;
import com.green.forest.api.ConfigService;
import com.green.forest.api.exception.invoke.NullActionException;
import com.green.forest.core.CoreUtil;
import com.green.forest.core.context.StaticContext;
import com.green.forest.core.deploy.ResourseService;
import com.green.forest.util.Util;

public class ActionServiceImpl implements ActionService {
	
	public ResourseService resourse;
	public ConfigService config;
	
	public StaticContext staticContext = new StaticContext();
	
	public ActionServiceImpl(ConfigService config, ResourseService resourseService) {
		this.resourse = resourseService;
		this.config = config;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public <I extends Serializable, O extends Serializable> O invoke(Action<I, O> action) {
		
		try {
			
			if(Util.isEmpty(action)){
				throw new NullActionException();
			}
			
			InvocationBlock block = new InvocationBlock(this, action);
			block.invoke();
			
			Object out = action.getOutput();
			return (O)out;
			
		} catch (Exception e) {
			throw CoreUtil.convertException(e, "can't invoke "+action);
		}

	}

}
