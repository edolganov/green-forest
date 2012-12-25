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
package com.gf.extra.scan;

import java.util.Set;

import com.gf.key.scan.ClassScannerKey;

/**
 * Interface for package scanning. Used for {@link com.gf.core.Engine#scanAndPut(String)} logic.
 * <br>There are two default implementations: 
 * <ul>
 * 	<li>{@link com.gf.core.scan.DefaultClassScanner}: for standalone app, Tomcat Web Server</li>
 *  <li>{@link com.gf.components.reflections.ReflectionsScanner}: for JBoss AS</li>
 * </ul>
 * 
 * You can use own scanner type by setting it to engine with config key {@link ClassScannerKey}:
 * <pre>
 * Engine engine = new Engine();
 * engine.setConfig(ClassScannerKey.class, YourScannerType.class);
 * engine.scanPackageForAnnotations("some.package");
 * </pre>
 *
 * @author Evgeny Dolganov
 * @see ClassScannerKey
 */
public interface ClassScanner {
	
	/**
	 * get children for parent class in packageRoot space
	 * @param packageRoot some package (for example "com.my.package")
	 * @param parentClass some parent type (without <tt>Object</tt> type)
	 * @return set of children classes or null
	 */
	Set<Class<?>> getClasses(String packageRoot, Class<?> parentClass);

}
