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
package com.gf.core.engine.handler;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.AbstractEngineTest;
import com.gf.core.engine.filter.model.InvocationContextSetterFilter;
import com.gf.core.engine.handler.model.InjectAllHandler;
import com.gf.core.engine.handler.model.InjectUnknownHandler;
import com.gf.core.engine.handler.model.InjectStaticHandler;
import com.gf.core.engine.handler.model.SubInvokeAction;
import com.gf.core.engine.handler.model.SubInvokeForInjectHandler;
import com.gf.core.engine.model.StaticServiceImpl;
import com.gf.core.engine.model.StaticServiceImpl2;
import com.gf.exception.invoke.ObjectToInjectNotFoundException;
import com.gf.test.action.EmptyAction;


public class InjectTest extends AbstractEngineTest {
	
	@Test
	public void test_inject_invocation_context_in_sub_invoke(){
		
		Engine engine = new Engine();
		engine.addToContext(new StaticServiceImpl());
		engine.putFilter(InvocationContextSetterFilter.class);
		engine.putHandler(SubInvokeForInjectHandler.class);
		engine.putHandler(InjectAllHandler.class);
		
		engine.invoke(new SubInvokeAction());
	}
	
	
	@Test
	public void test_inject_invocation_context_from_filter(){
		
		Engine engine = new Engine();
		engine.addToContext(new StaticServiceImpl());
		engine.putFilter(InvocationContextSetterFilter.class);
		engine.putHandler(InjectAllHandler.class);
		
		engine.invoke(new EmptyAction());
	}
	
	
	@Test
	public void test_inject_from_duplicates(){
		
		Engine engine = new Engine();
		engine.addToContext(new StaticServiceImpl());
		engine.addToContext(new StaticServiceImpl2());
		engine.putHandler(InjectStaticHandler.class);
		
		engine.invoke(new EmptyAction());
		
	}
	
	@Test(expected=ObjectToInjectNotFoundException.class)
	public void test_inject_unknown(){
		
		Engine engine = new Engine();
		engine.putHandler(InjectUnknownHandler.class);
		
		engine.invoke(new EmptyAction());
		
	}
	
	@Test
	public void test_inject(){
		
		Engine engine = new Engine();
		engine.addToContext(new StaticServiceImpl());
		engine.putHandler(InjectStaticHandler.class);
		
		engine.invoke(new EmptyAction());
		
	}

}
