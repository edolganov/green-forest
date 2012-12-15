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
package com.gf.core.scan;

import java.util.Set;

import com.gf.core.util.ScanUtil;
import com.gf.extra.scan.ClassScanner;

@SuppressWarnings({"rawtypes", "unchecked"})
public class DefaultClassScanner implements ClassScanner {

	
	@Override
	public Set<Class<?>> getClasses(String packageRoot, Class<?> parentClass) {
		
		ScanUtil<Class> scanUtil = new ScanUtil<Class>();
	    scanUtil.find(new ScanUtil.IsA(parentClass), packageRoot);
	    Set set = scanUtil.getClasses();
	    
		return set;
	}
	
	

}
