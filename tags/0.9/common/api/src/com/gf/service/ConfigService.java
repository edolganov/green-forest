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
package com.gf.service;

import java.util.Properties;

import com.gf.config.ConfigKey;
import com.gf.exception.config.EmptyClassException;
import com.gf.exception.config.GetConfigValueException;
import com.gf.exception.config.ParsePropertiesException;

/**
 * Interface for accessing and editing config values.
 * Main implementaion is {@link com.gf.core.Engine}.
 *
 * @author Evgeny Dolganov
 * @see ConfigKey
 * @see com.gf.core.Engine
 */
public interface ConfigService {
	
	/**
	 * Set config value by key type. You can create own config keys for using in handlers.
	 * @param keyType not null type
	 * @param value some value for this config type (can be null)
	 * @throws EmptyClassException if <tt>keyType</tt> is null
	 */
	<T> void setConfig(Class<? extends ConfigKey<T>> keyType, T value) throws EmptyClassException;
	
	/**
	 * Parse config values from Properties. Key must be a config key's <tt>Class</tt> string.
	 * <br>For example:
	 * <pre>
	 * //file config.properties
	 * some.package.SomeConfigKey=value 1
	 * some.package.OtherConfigKey=value 2
	 * com.gf.key.TraceHandlers=true 
	 * 
	 * //props
	 * InputStream is = getResourceAsStream("config.properties");
	 * Properties props = new Properties();
	 * props.load(is);
	 * </pre>
	 */
	void setConfigValues(Properties props) throws ParsePropertiesException;

	/**
	 * Get config value or default value or exception if key doesn't have default value.
	 */
	<T> T getConfig(ConfigKey<T> key) throws GetConfigValueException;

	/**
	 * Get boolean value of <tt>Boolean</tt> config key. If value is <tt>null</tt> return false.
	 * Anolog of {@link #getConfig(ConfigKey)}: <tt>Boolean.TRUE.equals(getConfig(key))</tt>
	 */
	boolean isTrueConfig(ConfigKey<Boolean> key) throws GetConfigValueException;

}
