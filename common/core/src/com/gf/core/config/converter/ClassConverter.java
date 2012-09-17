package com.gf.core.config.converter;



public class ClassConverter extends AbstractConverter<Class<?>>{

	@Override
	protected Class<?> convertToValue(String str) throws Exception {
		return Class.forName(str);
	}

	@Override
	protected String convertToStore(Class<?> value) throws Exception {
		return value.getName();
	}



}
