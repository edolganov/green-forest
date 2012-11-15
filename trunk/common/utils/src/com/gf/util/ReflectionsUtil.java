package com.gf.util;

import java.lang.reflect.Field;

public class ReflectionsUtil {
	
	
	@SuppressWarnings("unchecked")
	public static <T> T getField(Object obj, String fieldName) throws Exception {
		Field field = null;
		Class<?> curType = obj.getClass();
		while(curType != null){
			try {
				field = curType.getDeclaredField(fieldName);
				curType = null;
			}catch (NoSuchFieldException e) {
				curType = curType.getSuperclass();
			}
		}
		if(field == null){
			throw new NoSuchFieldException(fieldName);
		}
		
		field.setAccessible(true);
		return (T) field.get(obj);
	}

}
