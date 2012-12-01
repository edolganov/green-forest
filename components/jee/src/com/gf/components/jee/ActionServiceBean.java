package com.gf.components.jee;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.gf.Action;
import com.gf.common.ActionServiceFactory;
import com.gf.common.ActionServiceSingleton;
import com.gf.exception.ExceptionWrapper;
import com.gf.exception.invoke.InvocationException;
import com.gf.service.ActionService;

public abstract class ActionServiceBean implements ActionService, ActionServiceFactory {
	
	private static final ActionServiceSingleton serviceSingleton = new ActionServiceSingleton();
	
	protected ActionService actionService;
	
	
	@PostConstruct
	public void init(){
		actionService = serviceSingleton.getServiceAndRegisterClient(this);
	}
	
	@PreDestroy
	public void preDestroy(){
		serviceSingleton.unregisterClient();
	}
	
	@Override
	public <I, O> O invoke(Action<I, O> action) throws InvocationException, ExceptionWrapper, RuntimeException {
		return (O) actionService.invoke(action);
	}

	@Override
	public <I, O> O invokeUnwrap(Action<I, O> action) throws InvocationException, Exception {
		return (O) actionService.invokeUnwrap(action);
	}

}
