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
package ex01_invoke;

import actions.SomeAction;

import com.gf.core.Engine;

public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		engine.putHandler(SomeActonHandler.class);
		
		SomeAction action = new SomeAction("some data");
		String result = engine.invoke(action);
		System.out.println(result);
		
		SomeAction otherAction = new SomeAction("other data");
		engine.invoke(otherAction);
		String otherResult = otherAction.getOutput();
		System.out.println(otherResult);
		
		
	}

}
