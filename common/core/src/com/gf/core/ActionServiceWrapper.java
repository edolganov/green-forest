package com.gf.core;

import com.gf.Action;
import com.gf.exception.BaseException;
import com.gf.service.ActionService;

public abstract class ActionServiceWrapper implements ActionService {

	private ActionService actionService;

	public ActionServiceWrapper(ActionService actionService) {
		super();
		this.actionService = actionService;
	}
	
	
	@Override
	public <I, O> O invoke(Action<I, O> action) throws BaseException {
		return actionService.invoke(action);
	}

}
