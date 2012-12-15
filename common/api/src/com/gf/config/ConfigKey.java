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
package com.gf.config;

import com.gf.service.ConfigService;

/**
 * Abstract class for config key.
 * <br>Example:
 * <pre>
 * //key with default value
 * public class SomeConfigKey extends ConfigKey&lt;String&gt;{
 * 
 *   public boolean hasDefaultValue() {
 *     return true;
 *   }
 *   
 *   public String getDefaultValue() throws Exception {
 *     return "some default value";
 *   }
 * }
 * 
 * //key without default value
 * public class OtherConfigKey extends ConfigKey&lt;String&gt;{
 * 
 *   public boolean hasDefaultValue() {
 *     return false;
 *   }
 * }
 * 
 * //engine
 * Engine engine = new Engine();
 * 
 * String val1 = engine.getConfig(new SomeConfigKey());
 * System.out.println(val1); //<--------- print "some default value"
 *     
 * engine.setConfig(SomeConfigKey.class, "some value");
 * String val2 = engine.getConfig(new SomeConfigKey());
 * System.out.println(val2); //<--------- print "some value"
 *     
 * engine.getConfig(new OtherConfigKey()); //<---------- get GetConfigValueException
 * </pre>
 * @author Evgeny Dolganov
 * @see ConfigService
 * @see com.gf.core.Engine#setConfig(Class, Object)
 * @see com.gf.core.Engine#getConfig(ConfigKey)
 *
 */
public abstract class ConfigKey<T> {
	
	/**
	 * Do this key have a default value?
	 */
	public abstract boolean hasDefaultValue();
	
	/**
	 * Override this method for return default value.
	 */
	public T getDefaultValue() throws Exception {
		return null;
	}
	
	

}
