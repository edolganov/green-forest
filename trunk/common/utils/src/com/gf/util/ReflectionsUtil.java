package com.gf.util;

import java.lang.reflect.Field;

public class ReflectionsUtil {
	
	
	@SuppressWarnings("unchecked")
	public static <T> T getField(Object obj, String fieldName) throws Exception {
		Field field = obj.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		return (T) field.get(obj);
	}

}
