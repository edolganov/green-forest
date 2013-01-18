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

import com.gf.config.IntegerPair;

public class IntegerPairConverter extends AbstractConverter<IntegerPair>{

	@Override
	protected IntegerPair convertToValue(String str) throws Exception {
		String[] strings = str.split(",");
		if(strings.length != 2){
			throw new IllegalArgumentException("invalid string '"+str+"': mustbe int,int");
		}
		int a = Integer.parseInt(strings[0]);
		int b = Integer.parseInt(strings[1]);
		return new IntegerPair(a, b);
	}

	@Override
	protected String convertToStore(IntegerPair value) throws Exception {
		return ""+value.a+","+value.b;
	}

}
