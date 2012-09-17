package com.gf.core.action;

import java.io.Serializable;

import com.gf.Action;
import com.gf.ActionService;
import com.gf.ConfigService;
import com.gf.core.CoreUtil;
import com.gf.core.context.StaticContext;
import com.gf.core.deploy.ResourseService;
import com.gf.exception.invoke.NullActionException;
import com.gf.util.Util;

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
