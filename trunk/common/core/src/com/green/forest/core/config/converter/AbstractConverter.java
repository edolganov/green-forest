package com.green.forest.core.config.converter;

import com.green.forest.util.Util;


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
