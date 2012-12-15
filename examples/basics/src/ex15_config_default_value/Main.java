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
package ex15_config_default_value;

import com.gf.core.Engine;

import ex14_config.SomeConfigKey;

public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		
		String val1 = engine.getConfig(new SomeConfigKey());
		System.out.println(val1);
		
		engine.setConfig(SomeConfigKey.class, "some value");
		String val2 = engine.getConfig(new SomeConfigKey());
		System.out.println(val2);
		
		engine.getConfig(new OtherConfigKey());
		
	}

}
