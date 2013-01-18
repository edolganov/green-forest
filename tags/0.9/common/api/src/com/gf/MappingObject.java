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
package com.gf;

import com.gf.service.InvocationService;

/**
 * Base type for <tt>Handler</tt>, <tt>Interceptor</tt>
 *
 * @author Evgeny Dolganov
 * @see Handler
 * @see Interceptor
 *
 */
public abstract class MappingObject extends InvocationObject {
	
	private InvocationService invocation;
	
	/**
	 * Sub invoke the action.
	 * <p>Interceptors and Handlers can call <tt>subInvoke</tt> method.
	 * <br>In this case the order of processing:
	 * <ul>
	 * <li>Filters <b>(was called only once)</b></li>
	 * <li>Interceptors
	 * 		<ul>
	 * 			<li>Sub Interceptors</li>
	 * 			<li>Sub Handler</li>
	 * 		</ul>
	 * </li>
	 * <li>Handler
	 * 		<ul>
	 * 			<li>Sub Interceptors</li>
	 * 			<li>Sub Handler</li>
	 * 		</ul>
	 * </li>
	 * </ul>
	 */
	protected <I,O> O subInvoke(Action<I, O> action) throws Exception {
		return (O)invocation.subInvoke(action);
	}

	public void setInvocation(InvocationService invocation) {
		this.invocation = invocation;
	}
	

}
