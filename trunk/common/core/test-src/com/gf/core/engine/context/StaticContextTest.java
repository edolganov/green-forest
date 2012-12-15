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

import java.util.Collection;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.context.ContextServiceImpl;
import com.gf.core.engine.AbstractEngineTest;
import com.gf.core.engine.context.model.FirstStaticServiceExpectedFilter;
import com.gf.core.engine.context.model.SecondStaticServiceExpectedFilter;
import com.gf.core.engine.model.StaticServiceImpl;
import com.gf.core.engine.model.StaticServiceImpl2;
import com.gf.test.action.EmptyAction;
import com.gf.test.handler.EmptyHandler;
import com.gf.util.ReflectionsUtil;

public class StaticContextTest extends AbstractEngineTest {
	
	@Test
	public void test_for_duplicates() throws Exception{
		
		StaticServiceImpl service = new StaticServiceImpl();

		Engine engine = new Engine();
		engine.addToContext(service);
		engine.addToContext(service);
		
		ContextServiceImpl context = ReflectionsUtil.getField(engine, "context");
		Collection<Object> all = context.getStaticContextObjects();
		assertEquals(2, all.size());
		
	}
	
	@Test
	public void test_same_objects_order(){
		
		Engine engine = new Engine();
		engine.addToContext(new StaticServiceImpl2());
		engine.addToContext(new StaticServiceImpl());
		engine.putFilter(FirstStaticServiceExpectedFilter.class);
		engine.putHandler(EmptyHandler.class);
		engine.invoke(new EmptyAction());
		
		Engine engine2 = new Engine();
		engine2.addToContext(new StaticServiceImpl());
		engine2.addToContext(new StaticServiceImpl2());
		engine2.putFilter(SecondStaticServiceExpectedFilter.class);
		engine2.putHandler(EmptyHandler.class);
		engine2.invoke(new EmptyAction());
		
	}
	

}
