package com.gf.core.action;

import java.io.Serializable;

import com.gf.Action;
import com.gf.core.context.StaticContext;
import com.gf.core.deploy.ResourseService;
import com.gf.core.util.CoreUtil;
import com.gf.exception.invoke.NullActionException;
import com.gf.service.ActionService;
import com.gf.service.ConfigService;
import com.gf.util.Util;

public class ActionServiceImpl implements ActionService {
	
	public ResourseService resourse;
	public ConfigService config;
	public StaticContext staticContext;
	
	public ActionServiceImpl(ConfigService config, ResourseService resourseService, StaticContext staticContext) {
		this.resourse = resourseService;
		this.config = config;
		this.staticContext = staticContext;
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
