/*
 * Copyright 2012 Evgeny Dolganov (evgenij.dolganov@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gf.components.jee;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.gf.Action;
import com.gf.common.ActionServiceFactory;
import com.gf.common.ActionServiceSingleton;
import com.gf.exception.ExceptionWrapper;
import com.gf.exception.invoke.InvocationException;
import com.gf.service.ActionService;

/**
 * Abstract class of EJB bean for storing {@link ActionService} singleton object.
 *
 * @author Evgeny Dolganov
 *
 */
public abstract class ActionServiceBean implements ActionService, ActionServiceFactory {
	
	/**
	 * Singleton storage
	 */
	private static final ActionServiceSingleton serviceSingleton = new ActionServiceSingleton();
	
	/**
	 * Link to action service from current object
	 */
	protected ActionService actionService;
	
	
	/**
	 * Create and store ActionService object (only one time)
	 */
	@PostConstruct
	public void init(){
		actionService = serviceSingleton.getServiceAndRegisterClient(this);
	}
	
	/**
	 * Create singleton <tt>ActionService</tt>
	 */
	public abstract ActionService createActionService();
	
	@PreDestroy
	public void preDestroy(){
		serviceSingleton.unregisterClient();
	}
	
	public <I, O> O invoke(Action<I, O> action) throws InvocationException, ExceptionWrapper, RuntimeException {
		return (O) actionService.invoke(action);
	}

	public <I, O> O invokeUnwrap(Action<I, O> action) throws InvocationException, Exception {
		return (O) actionService.invokeUnwrap(action);
	}

}
