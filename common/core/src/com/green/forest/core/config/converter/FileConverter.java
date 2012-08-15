package com.green.forest.core.config.converter;

import java.io.File;



public class FileConverter extends AbstractConverter<File>{

	@Override
	protected File convertToValue(String str) throws Exception {
		return new File(str);
	}

	@Override
	protected String convertToStore(File value) throws Exception {
		return value.getPath();
	}



}
