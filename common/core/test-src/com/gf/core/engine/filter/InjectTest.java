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
package com.gf.core.engine.filter;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.AbstractEngineTest;
import com.gf.core.engine.filter.model.InjectAllFilter;
import com.gf.core.engine.filter.model.InjectUnknownFilter;
import com.gf.core.engine.filter.model.InjectStaticFilter;
import com.gf.core.engine.filter.model.InvocationContextSetterFilter;
import com.gf.core.engine.model.StaticServiceImpl;
import com.gf.core.engine.model.StaticServiceImpl2;
import com.gf.exception.invoke.ObjectToInjectNotFoundException;
import com.gf.test.action.EmptyAction;
import com.gf.test.handler.EmptyHandler;

public class InjectTest extends AbstractEngineTest {
	
	
	
	@Test
	public void test_inject_invocation_context_after_setter_filter(){
		
		Engine engine = new Engine();
		engine.addToContext(new StaticServiceImpl());
		engine.putFilter(InvocationContextSetterFilter.class);
		engine.putFilter(InjectAllFilter.class);
		engine.putHandler(EmptyHandler.class);
		
		engine.invoke(new EmptyAction());		
	}
	
	
	@Test
	public void test_inject_from_duplicates(){
		
		Engine engine = new Engine();
		engine.addToContext(new StaticServiceImpl());
		engine.addToContext(new StaticServiceImpl2());
		engine.putFilter(InjectStaticFilter.class);
		engine.putHandler(EmptyHandler.class);
		
		engine.invoke(new EmptyAction());
		
	}
	
	@Test(expected=ObjectToInjectNotFoundException.class)
	public void test_inject_unknown(){
		
		Engine engine = new Engine();
		engine.putFilter(InjectUnknownFilter.class);
		engine.putHandler(EmptyHandler.class);
		
		engine.invoke(new EmptyAction());
		
	}
	
	@Test
	public void test_inject(){
		
		Engine engine = new Engine();
		engine.addToContext(new StaticServiceImpl());
		engine.putFilter(InjectStaticFilter.class);
		engine.putHandler(EmptyHandler.class);
		
		engine.invoke(new EmptyAction());
		
	}
	

}
