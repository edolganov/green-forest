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
package com.gf.components.log.apache;

import com.gf.log.LogChecker;

public class LogCheckerImpl implements LogChecker {

	@Override
	public boolean isValid() {
		
		try {
			Class.forName("org.apache.commons.logging.LogFactory");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String getProviderClass() {
		String packageName = this.getClass().getPackage().getName();
		return packageName+".ApacheLogProvider";
	}

}
