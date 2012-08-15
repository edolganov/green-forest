package com.green.forest.core.config.converter;



public class LongConverter extends AbstractConverter<Long>{

	@Override
	protected Long convertToValue(String str) throws Exception {
		return Long.parseLong(str);
	}

	@Override
	protected String convertToStore(Long value) throws Exception {
		return value.toString();
	}



}
