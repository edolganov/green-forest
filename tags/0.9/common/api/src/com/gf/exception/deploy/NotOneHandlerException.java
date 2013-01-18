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
package com.gf.exception.deploy;

import java.util.Set;

/**
 * There is already some <tt>Handler</tt> for mapped <tt>Action</tt>.
 * <p>Example:
 * <pre>
 * &#064;Mapping(SomeAction.class)
 * public class HandlerA extends Handler&lt;SomeAction&gt;{ ... }
 * 
 * &#064;Mapping(SomeAction.class)
 * public class HandlerB extends Handler&lt;SomeAction&gt;{ ... }
 * 
 * Engine engine = new Engine();
 * engine.putHandler(HandlerA.class);
 * engine.putHandler(HandlerB.class); //throws NotOneHandlerException
 * 
 * </pre>
 *
 * @author Evgeny Dolganov
 *
 */
public class NotOneHandlerException extends DeployException {
	
	private static final long serialVersionUID = 8460269856330004245L;

	public NotOneHandlerException(Class<?> target, Set<Class<?>> handlers) {
		super("target ["+target+"] has many handlers "+handlers);
	}
	
	

}
