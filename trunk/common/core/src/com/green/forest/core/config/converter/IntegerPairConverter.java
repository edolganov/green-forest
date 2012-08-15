package com.green.forest.core.config.converter;

import com.green.forest.api.config.IntegerPair;

public class IntegerPairConverter extends AbstractConverter<IntegerPair>{

	@Override
	protected IntegerPair convertToValue(String str) throws Exception {
		String[] strings = str.split(",");
		if(strings.length != 2){
			throw new IllegalArgumentException("invalid string '"+str+"': mustbe int,int");
		}
		int a = Integer.parseInt(strings[0]);
		int b = Integer.parseInt(strings[1]);
		return new IntegerPair(a, b);
	}

	@Override
	protected String convertToStore(IntegerPair value) throws Exception {
		return ""+value.a+","+value.b;
	}

}
