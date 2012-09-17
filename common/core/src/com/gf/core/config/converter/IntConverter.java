package com.gf.core.config.converter;



public class IntConverter extends AbstractConverter<Integer>{

	@Override
	protected Integer convertToValue(String str) throws Exception {
		return Integer.parseInt(str);
	}

	@Override
	protected String convertToStore(Integer value) throws Exception {
		return value.toString();
	}



}
