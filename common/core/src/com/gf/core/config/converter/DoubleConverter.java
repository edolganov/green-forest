package com.gf.core.config.converter;



public class DoubleConverter extends AbstractConverter<Double>{

	@Override
	protected Double convertToValue(String str) throws Exception {
		return Double.parseDouble(str);
	}

	@Override
	protected String convertToStore(Double value) throws Exception {
		return value.toString();
	}



}
