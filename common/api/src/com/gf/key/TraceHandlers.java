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
package com.gf.key;

import com.gf.Action;
import com.gf.config.ConfigKey;
import com.gf.extra.trace.Trace;

public class TraceHandlers extends ConfigKey<Boolean> {
	
	public static final String ATTR_KEY = TraceHandlers.class.getName()+".Trace";

	@Override
	public boolean hasDefaultValue() {
		return true;
	}
	
	@Override
	public Boolean getDefaultValue() throws Exception {
		return Boolean.FALSE;
	}
	
	public static Trace getOrCreateTrace(Action<?, ?> action){
		Trace trace = getTrace(action);
		if(trace == null){
			trace = new Trace();
			setTrace(action, trace);
		}
		return trace;
	}
	
	public static Trace getTrace(Action<?, ?> action){
		return (Trace) action.getAttr(ATTR_KEY);
	}
	
	public static void setTrace(Action<?, ?> action, Trace trace) {
		action.putAttr(ATTR_KEY, trace);
	}

}
