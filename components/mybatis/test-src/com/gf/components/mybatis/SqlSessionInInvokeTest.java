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
package com.gf.components.mybatis;

import org.junit.Test;

import com.gf.components.mybatis.model.HandlerWithNoSettings;
import com.gf.components.mybatis.model.HandlerWithSettings;
import com.gf.core.Engine;
import com.gf.test.action.EmptyAction;

public class SqlSessionInInvokeTest extends AbstractTest {
	
	
	@Test
	public void test_invoke_with_settings(){
		
		Engine engine = new Engine();
		engine.addToContext(dataSource);
		engine.addToContext(sqlSessionFactory);
		engine.putFilter(SqlSessionInInvoke.class);
		engine.putHandler(HandlerWithSettings.class);
		
		engine.invoke(new EmptyAction());
		
	}
	
	
	@Test
	public void test_invoke_default(){
		
		Engine engine = new Engine();
		engine.addToContext(dataSource);
		engine.addToContext(sqlSessionFactory);
		engine.putFilter(SqlSessionInInvoke.class);
		engine.putHandler(HandlerWithNoSettings.class);
		
		engine.invoke(new EmptyAction());
		
	}
	

}
