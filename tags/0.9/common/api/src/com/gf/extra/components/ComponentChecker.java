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
package com.gf.extra.components;

public abstract class ComponentChecker {
	
	public abstract boolean isValid();
	
	public abstract String getComponentClass();
	
	
	protected boolean exists(String classPath){
		try {
			Class.forName(classPath);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	protected String getWithCurrentPackage(String className){
		String packageName = this.getClass().getPackage().getName();
		return packageName+"."+className;
	}
	
	public static final Class<?> findAndCheckBy(String checkerClass){
		try {
			ComponentChecker logChecker = (ComponentChecker)Class.forName(checkerClass).newInstance();
			boolean valid = logChecker.isValid();
			if( ! valid){
				return null;
			}
			
			String componentClass = logChecker.getComponentClass();
			return Class.forName(componentClass);
			
		}catch (Exception e) {
			//no provider
			return null;
		}
	}
	

}
