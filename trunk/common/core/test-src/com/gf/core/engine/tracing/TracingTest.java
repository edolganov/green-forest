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
package com.gf.core.engine.tracing;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.AbstractEngineTest;
import com.gf.core.engine.tracing.model.CallSubEngineWithDiffActionFilter;
import com.gf.core.engine.tracing.model.CallSubEngineWithDiffActionHandler;
import com.gf.core.engine.tracing.model.CallSubEngineWithDiffActionInterceptor;
import com.gf.core.engine.tracing.model.CallSubEngineWithSameActionFilter;
import com.gf.core.engine.tracing.model.CallSubEngineWithSameActionHandler;
import com.gf.core.engine.tracing.model.CallSubEngineWithSameActionInterceptor;
import com.gf.extra.trace.Trace;
import com.gf.key.TraceHandlers;
import com.gf.test.action.EmptyAction;
import com.gf.test.action.StringAction;
import com.gf.test.handler.EmptyHandler;
import com.gf.test.handler.StringEcho;
import com.gf.util.Util;

@SuppressWarnings("unchecked")
public class TracingTest extends AbstractEngineTest {
	
	
	@Test
	public void test_trace_sub_engine_with_disable_root_tracing(){
		
		Engine engine = new Engine();
		engine.setConfig(TraceHandlers.class, false);
		engine.putFilter(CallSubEngineWithDiffActionFilter.class);
		engine.putInterceptor(CallSubEngineWithDiffActionInterceptor.class);
		engine.putHandler(CallSubEngineWithDiffActionHandler.class);
		
		Engine subEngine = new Engine();
		subEngine.putHandler(EmptyHandler.class);
		subEngine.setConfig(TraceHandlers.class, true);
		
		engine.addToContext(subEngine);
		
		EmptyAction action = new EmptyAction();
		engine.invoke(action);
		
		Trace trace = TraceHandlers.getTrace(action);
		assertNull(trace);
	}
	
	@Test
	public void test_trace_sub_engine_with_different_action(){
		
		Engine engine = new Engine();
		engine.setConfig(TraceHandlers.class, true);
		engine.putFilter(CallSubEngineWithDiffActionFilter.class);
		engine.putInterceptor(CallSubEngineWithDiffActionInterceptor.class);
		engine.putHandler(CallSubEngineWithDiffActionHandler.class);
		
		Engine subEngine = new Engine();
		subEngine.putHandler(EmptyHandler.class);
		
		engine.addToContext(subEngine);
		
		EmptyAction action = new EmptyAction();
		engine.invoke(action);
		
		checkTrace(action, 
				CallSubEngineWithDiffActionFilter.class,
					Util.list(EmptyHandler.class),
				CallSubEngineWithDiffActionInterceptor.class,
					Util.list(EmptyHandler.class),
				CallSubEngineWithDiffActionHandler.class,
					Util.list(EmptyHandler.class)
				);
	}
	
	@Test
	public void test_trace_sub_engine_with_same_action(){

		Engine engine = new Engine();
		engine.setConfig(TraceHandlers.class, true);
		engine.putFilter(CallSubEngineWithSameActionFilter.class);
		engine.putInterceptor(CallSubEngineWithSameActionInterceptor.class);
		engine.putHandler(CallSubEngineWithSameActionHandler.class);
		
		Engine subEngine = new Engine();
		subEngine.putHandler(EmptyHandler.class);
		
		engine.addToContext(subEngine);
		
		EmptyAction action = new EmptyAction();
		engine.invoke(action);
		
		checkTrace(action, 
				CallSubEngineWithSameActionFilter.class,
					Util.list(EmptyHandler.class),
				CallSubEngineWithSameActionInterceptor.class,
					Util.list(EmptyHandler.class),
				CallSubEngineWithSameActionHandler.class,
					Util.list(EmptyHandler.class)
				);
		
	}
	
	@Test
	public void test_enable_tracing(){
		
		Engine engine = new Engine();
		engine.putHandler(StringEcho.class);
		engine.setConfig(TraceHandlers.class, true);
		
		StringAction action = new StringAction();
		engine.invoke(action);
		
		Trace trace = TraceHandlers.getTrace(action);
		assertNotNull(trace);
	}
	
	
	@Test
	public void test_default_not_tracing(){
		
		Engine engine = new Engine();
		engine.putHandler(StringEcho.class);
		
		StringAction action = new StringAction();
		engine.invoke(action);
		
		Trace trace = TraceHandlers.getTrace(action);
		assertNull(trace);

	}

}
