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
package com.gf.components.reflections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.reflections.vfs.Vfs;

import com.gf.extra.scan.ClassScanner;
import com.gf.log.Log;
import com.gf.log.LogFactory;
import com.google.common.collect.Multimap;

public class ReflectionsScanner implements ClassScanner {
	
	static {
		Vfs.addDefaultURLTypes(new JBossVFSUrlType());
	}
	
	Log log = LogFactory.getLog(getClass());

	@Override
	public Set<Class<?>> getClasses(String packageRoot, Class<?> parentClass) {
		


		SubTypesScanner subTypesScanner = new SubTypesScanner();
		
		new Reflections(new ConfigurationBuilder()
        	.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packageRoot)))
        	.setUrls(ClasspathHelper.forPackage(packageRoot))
        	.setScanners(subTypesScanner));
		
		Multimap<String, String> map = subTypesScanner.getStore();
		Set<Class<?>> out = findAbsoluteSubTypes(map, parentClass);
		return out;
	}

	private Set<Class<?>> findAbsoluteSubTypes(Multimap<String, String> map, Class<?> parentClass) {
		
		
		HashSet<Class<?>> out = new HashSet<Class<?>>();
		HashSet<String> processed = new HashSet<String>();
		
		Collection<String> values = map.values();
		for(String candidat : values){
			if(processed.contains(candidat)){
				continue;
			}
			try {
				Class<?> clazz = Class.forName(candidat);
				if(parentClass.isAssignableFrom(clazz)){
					out.add(clazz);
				}
			} catch (ClassNotFoundException e) {
				log.error("can't get class by name "+candidat, e);
			}
			processed.add(candidat);
		}
		return out;
	}

}
