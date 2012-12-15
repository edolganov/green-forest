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
