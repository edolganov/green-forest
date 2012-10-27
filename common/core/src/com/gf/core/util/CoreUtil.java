package com.gf.core.util;

import com.gf.exception.BaseException;
import com.gf.exception.ExceptionWrapper;


public class CoreUtil {
	
	
	@SuppressWarnings("unchecked")
	public static <T> T createInstance(Class<?> type) throws RuntimeException {
		try {
			return (T)type.newInstance();
		} catch (Exception e) {
			throw new ExceptionWrapper("cannot create instance of "+type, e);
		}
	}
	
	public static RuntimeException convertException(Exception e, String info){
		if(e instanceof BaseException){
			return (BaseException)e;
		}
		else if(e instanceof RuntimeException){
			return (RuntimeException)e;
		}
		else {
			return new ExceptionWrapper(info, e);
		}
	}

}
