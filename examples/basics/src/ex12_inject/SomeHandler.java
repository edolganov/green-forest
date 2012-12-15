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
package ex12_inject;

import actions.SomeAction;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;


@Mapping(SomeAction.class)
public class SomeHandler extends Handler<SomeAction>{
	
	@Inject
	SomeService service;

	@Override
	public void invoke(SomeAction action) throws Exception {
		
		service.someMethod();
		
	}

}
