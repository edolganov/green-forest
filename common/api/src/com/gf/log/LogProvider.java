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
package com.gf.log;

/**
 * Factory interface for creating {@link Log} implementations.
 * For setup implementation use <tt>"com.gf.log.LogProvider"</tt> system property:
 * <pre>
 * //setup on start
 * java -Dcom.gf.log.LogProvider=foo.blah.CustomLogProvider
 * 
 * //or setup in app BEFORE first call of Green-forest
 * static {
 *   System.setProperty("com.gf.log.LogProvider", "foo.blah.CustomLogProvider");
 * }
 * </pre>
 * 
 * @author Evgeny Dolganov
 *
 */
public interface LogProvider {
	
	Log getLog(Class<?> clazz);

}
