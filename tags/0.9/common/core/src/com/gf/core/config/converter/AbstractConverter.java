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
package com.gf.core.config.converter;

import com.gf.util.Util;


public abstract class AbstractConverter<T> {
	
	public T toValue(String str){
		
		if(str == null){
			return null;
		}
		
		if(str.length() == 0 && !canConvertEmptyString()){
			return null;
		}
		
		try {
			return (T) convertToValue(str);
		}catch (Exception e) {
			throw new IllegalArgumentException("can't convert string to value", e);
		}
	}
	
	public String toStore(T value) {
		if(Util.isEmpty(value)){
			return "";
		}
		
		try {
			return convertToStore(value);
		}catch (Exception e) {
			throw new IllegalArgumentException("can't convert value to string", e);
		}
	}
	
	protected boolean canConvertEmptyString(){
		return false;
	}
	
	protected abstract T convertToValue(String str) throws Exception;
	
	protected abstract String convertToStore(T value) throws Exception;

}
