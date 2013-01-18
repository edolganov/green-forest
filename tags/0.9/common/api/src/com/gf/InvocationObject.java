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
package com.gf;

import com.gf.config.ConfigKey;
import com.gf.log.Log;
import com.gf.log.LogFactory;
import com.gf.service.ConfigService;

/**
 * Base type for <tt>Handler</tt>, <tt>Interceptor</tt>, <tt>Filter</tt>
 *
 * @author Evgeny Dolganov
 * @see Handler
 * @see Interceptor
 * @see Filter
 *
 */
public abstract class InvocationObject {
	
	/**
	 * Default logger
	 */
	protected Log log = LogFactory.getLog(getClass());
	
	/**
	 * Config service.
	 * <br>Example of using:
	 * <pre>
	 * //key
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
	 * //handler
	 * &#064;Mapping(SomeAction.class)
	 * public class SomeHandler extends Handler&lt;SomeAction&gt;{
	 * 
	 *   public void invoke(SomeAction action) throws Exception {
	 *     String value = config.getConfig(new SomeConfigKey());
	 *     log.info(value);
	 *   }
	 * }
	 * 
	 * //engine
	 * Engine engine = new Engine();
	 * engine.putHandler(SomeHandler.class);
	 * engine.setConfig(SomeConfigKey.class, "some value");
	 * engine.invoke(new SomeAction());
	 * </pre>
	 * 
	 * @see ConfigKey
	 */
	protected ConfigService config;

	
	public void setConfigService(ConfigService config) {
		this.config = config;
	}
	
	
	
}
