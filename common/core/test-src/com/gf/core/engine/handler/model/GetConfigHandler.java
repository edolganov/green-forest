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
package com.gf.core.engine.handler.model;

import com.gf.Handler;
import com.gf.annotation.Mapping;
import com.gf.test.action.EmptyAction;
import com.gf.util.junit.AssertExt;


@Mapping(EmptyAction.class)
public class GetConfigHandler extends Handler<EmptyAction>{

	@Override
	public void invoke(EmptyAction action) throws Exception {
		
		String value = config.getConfig(new TestConfig());
		AssertExt.assertEquals("test", value); 
		
	}

}
