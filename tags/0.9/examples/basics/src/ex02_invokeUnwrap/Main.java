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
package ex02_invokeUnwrap;

import actions.SomeAction;

import com.gf.core.Engine;
import com.gf.exception.ExceptionWrapper;


public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		engine.putHandler(ActionHandlerWithException.class);
		
		//1. use unwrap
		try {
			engine.invokeUnwrap(new SomeAction());
		}catch (Exception e) {
			System.out.println(e);
		}
		
		//2. use wrap
		try {
			engine.invoke(new SomeAction());
		}catch (ExceptionWrapper e) {
			Exception myExp = e.getOriginal();
			System.out.println(myExp);
		}
		
		//3. not handle
		engine.invoke(new SomeAction()); //throws ExceptionWrapper
		
		
	}

}
