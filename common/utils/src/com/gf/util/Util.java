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
package com.gf.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class Util {

	public static boolean isEmpty(Object o) {
		return o == null;
	}

	public static boolean isEmpty(Collection<?> col) {
		return col == null || col.size() == 0;
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isEmpty(Map<?, ?> map) {
		return map == null || map.size() == 0;
	}

	public static boolean isEmpty(Object[] arr) {
		return arr == null || arr.length == 0;
	}

	public static String randomUUID() {
		UUID uuid = UUID.randomUUID();
		String out = uuid.toString();
		return out.toString();
	}

	public static void checkArgumentForEmpty(Object ob, String errorMsg)
			throws IllegalArgumentException {
		if (isEmpty(ob)) {
			throw new IllegalArgumentException(errorMsg);
		}
	}

	public static void checkArgument(boolean state, String errorMsg)
			throws IllegalStateException {
		if (!state) {
			throw new IllegalArgumentException(errorMsg);
		}
	}

	public static void checkState(boolean state, String errorMsg)
			throws IllegalStateException {
		if (!state) {
			throw new IllegalStateException(errorMsg);
		}
	}

	public static <T> ArrayList<T> list(T... elems) {
		ArrayList<T> list = new ArrayList<T>();
		for (T elem : elems) {
			list.add(elem);
		}
		return list;
	}

	public static <T> ArrayList<T> toArrayList(Collection<T> collection) {

		if (collection == null) {
			return null;
		}

		ArrayList<T> out = null;
		if (collection instanceof ArrayList<?>) {
			out = (ArrayList<T>) collection;
		} else {
			out = new ArrayList<T>(collection);
		}
		return out;
	}
	
	public static int tryParse(String str, int defaultVal){
		if(isEmpty(str)){
			return defaultVal;
		}
		
		try {
			return (int) Integer.parseInt(str);
		}catch (Exception e) {
			return defaultVal;
		}
	}
	
    public static String toObjectString(Object ob) {
        return ob.getClass().getName() + "@" + Integer.toHexString(ob.hashCode());
    }

}
