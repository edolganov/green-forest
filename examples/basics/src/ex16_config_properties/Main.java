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
package ex16_config_properties;

import java.io.InputStream;
import java.util.Properties;

import com.gf.core.Engine;
import com.gf.key.TraceHandlers;

import ex14_config.SomeConfigKey;
import ex15_config_default_value.OtherConfigKey;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		InputStream is = Main.class.getResourceAsStream("/ex16_config_properties/config.properties");
		
		Properties props = new Properties();
		props.load(is);
		
		Engine engine = new Engine();
		engine.setConfigValues(props);
		
		String val1 = engine.getConfig(new SomeConfigKey());
		String val2 = engine.getConfig(new OtherConfigKey());
		boolean val3 = engine.isTrueConfig(new TraceHandlers());
		System.out.println(val1);
		System.out.println(val2);
		System.out.println(val3);
		
	}

}
