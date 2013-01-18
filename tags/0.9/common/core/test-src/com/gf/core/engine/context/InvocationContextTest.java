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
package com.gf.core.engine.context;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.AbstractEngineTest;
import com.gf.core.engine.context.model.FirstInvocationContextSetterFilter;
import com.gf.core.engine.context.model.FirstInvocationServiceExpectedFilter;
import com.gf.core.engine.context.model.InvocationContextDuplicatesChecker;
import com.gf.core.engine.context.model.RewriteStaticContextFilter;
import com.gf.core.engine.context.model.SecondInvocationContextSetterFilter;
import com.gf.core.engine.context.model.SecondInvocationServiceExpectedFilter;
import com.gf.core.engine.context.model.SecondStaticServiceExpectedFilter;
import com.gf.core.engine.model.StaticServiceImpl;
import com.gf.test.action.EmptyAction;
import com.gf.test.handler.EmptyHandler;

public class InvocationContextTest extends AbstractEngineTest {
	
	
	@Test
	public void test_rewrite_static_context(){
		
		Engine engine = new Engine();
		engine.addToContext(new StaticServiceImpl());
		engine.putFilter(RewriteStaticContextFilter.class);
		engine.putFilter(SecondStaticServiceExpectedFilter.class);
		engine.putHandler(EmptyHandler.class);
		engine.invoke(new EmptyAction());
	}
	
	@Test
	public void test_for_duplicates(){
		
		Engine engine = new Engine();
		engine.putFilter(InvocationContextDuplicatesChecker.class);
		engine.putHandler(EmptyHandler.class);
		engine.invoke(new EmptyAction());
		
	}
	
	@Test
	public void test_same_objects_order(){
		
		Engine engine = new Engine();
		engine.putFilter(FirstInvocationContextSetterFilter.class);
		engine.putFilter(FirstInvocationServiceExpectedFilter.class);
		engine.putHandler(EmptyHandler.class);
		engine.invoke(new EmptyAction());
		
		Engine engine2 = new Engine();
		engine2.putFilter(SecondInvocationContextSetterFilter.class);
		engine2.putFilter(SecondInvocationServiceExpectedFilter.class);
		engine2.putHandler(EmptyHandler.class);
		engine2.invoke(new EmptyAction());
	}
	

}
