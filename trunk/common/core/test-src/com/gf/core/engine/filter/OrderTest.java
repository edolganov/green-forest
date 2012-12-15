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
import com.gf.core.engine.filter.model.BeginFilter;
import com.gf.core.engine.filter.model.EndFilter;
import com.gf.core.engine.filter.model.FirstFilter;
import com.gf.core.engine.filter.model.SecondFilter;
import com.gf.core.engine.filter.model.UnorderedFilter;
import com.gf.test.action.StringAction;
import com.gf.test.handler.StringEcho;

public class OrderTest extends AbstractEngineTest {

	
	@Test
	public void test_order_by_annotation(){
		
		Engine engine = new Engine();
		enableTracing(engine);
		
		engine.putHandler(StringEcho.class);
		engine.putFilter(FirstFilter.class);
		engine.putFilter(SecondFilter.class);
		engine.putFilter(BeginFilter.class);
		engine.putFilter(EndFilter.class);
		engine.putFilter(UnorderedFilter.class);
		
		StringAction action = new StringAction("test");
		engine.invoke(action);
		
		checkTrace(action,
				BeginFilter.class,
				FirstFilter.class,
				SecondFilter.class,
				UnorderedFilter.class,
				EndFilter.class,
				StringEcho.class);
	}
	

}
