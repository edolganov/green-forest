package com.green.forest.core;

import com.green.forest.api.exception.UnexpectedException;


public class CoreUtil {
	
	
	@SuppressWarnings("unchecked")
	public static <T> T createInstance(Class<?> type) throws UnexpectedException {
		try {
			return (T)type.newInstance();
		} catch (Exception e) {
			throw new UnexpectedException("cannot create instance of "+type, e);
		}
	}

}
