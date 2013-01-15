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
package com.gf.extra.invocation;

import java.util.Collection;

import com.gf.InvocationObject;
import com.gf.log.Log;
import com.gf.log.LogFactory;

public abstract class InvocationObjectInitializer {
	
	protected Log log = LogFactory.getLog(getClass());
	
	public void beforeObjectInited(InvocationObject obj, Collection<Object> context) throws Exception {
		//override if need
	};
	
	public void afterObjectInited(InvocationObject obj, Collection<Object> context) throws Exception {
		//override if need
	};
	
	
	@SuppressWarnings("unchecked")
	protected <T> T findByType(Collection<Object> context, Class<T> type){
		for(Object object : context){
			if(type.isAssignableFrom(object.getClass())){
				return (T)object;
			}
		}
		return null;
	}
	
	

}
