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

import java.util.Collection;
import java.util.Iterator;

import com.gf.util.Util;



@SuppressWarnings("rawtypes")
public abstract class AbstractCollectionConverter<T extends Collection> extends AbstractConverter<T>{
	
	Class<T> collectionType;
	
	public AbstractCollectionConverter(Class<T> collectionType) {
		this.collectionType = collectionType;
	}
	
	@Override
	protected boolean canConvertEmptyString() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T convertToValue(String str) throws Exception {
		if(Util.isEmpty(str)){
			return (T)collectionType.newInstance();
		}
		
		T out = collectionType.newInstance();
		String[] strings = str.split(",");
		for(String val : strings){
			out.add(val);
		}
		return (T)out;
	}

	@Override
	protected String convertToStore(T value) throws Exception {
		if(value.size() == 0){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		Iterator it = value.iterator();
		while(it.hasNext()){
			Object str = it.next();
			sb.append(str);
			if(it.hasNext()){
				sb.append(',');
			}
		}
		return sb.toString();
		
	}



}
