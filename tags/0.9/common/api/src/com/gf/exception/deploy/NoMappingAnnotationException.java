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

import com.gf.MappingObject;
import com.gf.annotation.Mapping;

/**
 * <tt>MappingObject</tt> do not contains <tt>@Mapping</tt> annotation.
 * <p>Example:
 * <pre>
 * 
 * //@Mapping(SomeAction.class)
 * public class HandlerWithNoMapping extends Handler<SomeAction>{ 
 * 	... 
 * }
 * 
 * Engine engine = new Engine();
 * engine.putHandler(HandlerWithNoMapping.class); //throws NoMappingAnnotationException
 * 
 * </pre>
 * @author Evgeny Dolganov
 * @see Mapping
 * @see MappingObject
 *
 */
public class NoMappingAnnotationException extends DeployException {

	private static final long serialVersionUID = 3050383810011881818L;

	public NoMappingAnnotationException(Class<?> handler) {
		super("no mapping for ["+handler+"]");
	}

}
