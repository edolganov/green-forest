package com.gf.core;

import com.gf.Action;
import com.gf.exception.ExceptionWrapper;
import com.gf.exception.invoke.InvocationException;
import com.gf.service.ActionService;

public abstract class ActionServiceWrapper implements ActionService {

	protected ActionService actionService;

	public ActionServiceWrapper(ActionService actionService) {
		super();
		this.actionService = actionService;
	}
	
	
	@Override
	public <I, O> O invoke(Action<I, O> action) throws InvocationException, ExceptionWrapper, RuntimeException {
		return actionService.invoke(action);
	}
	
	@Override
	public <I, O> O invokeUnwrap(Action<I, O> action) throws InvocationException, Exception {
		return actionService.invokeUnwrap(action);
	}

}
