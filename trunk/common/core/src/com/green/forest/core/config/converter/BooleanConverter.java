package com.green.forest.core.config.converter;



public class BooleanConverter extends AbstractConverter<Boolean>{
	

	@Override
	protected Boolean convertToValue(String str) throws Exception {
		return Boolean.parseBoolean(str);
	}

	@Override
	protected String convertToStore(Boolean value) throws Exception {
		return value.toString();
	}



}
