package com.gf.core.config.converter;



public class StringConverter extends AbstractConverter<String>{
	
	@Override
	protected boolean canConvertEmptyString() {
		return true;
	}

	@Override
	protected String convertToValue(String str) throws Exception {
		return str;
	}

	@Override
	protected String convertToStore(String value) throws Exception {
		return value;
	}



}
