package com.gf.core.util;

import com.gf.exception.BaseException;
import com.gf.exception.ExternalException;


public class CoreUtil {
	
	
	@SuppressWarnings("unchecked")
	public static <T> T createInstance(Class<?> type) throws ExternalException {
		try {
			return (T)type.newInstance();
		} catch (Exception e) {
			throw new ExternalException("cannot create instance of "+type, e);
		}
	}
	
	public static RuntimeException convertException(Exception e, String info){
		if(e instanceof BaseException){
			return (BaseException)e;
		} else {
			return new ExternalException(info, e);
		}
	}

}