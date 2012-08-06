package com.green.forest.util;

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
	
	public static <T extends Throwable> void checkEmpty(Object obj, Class<T> exClass) throws T {
		if(Util.isEmpty(obj)){
			T t = null;
			try {
				t = (T)exClass.newInstance();
			}catch(Exception ex){
				throw new IllegalStateException(ex);
			}
			throw t;
			
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

}
