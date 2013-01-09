package com.gf.log;

public class CustomLogProvider implements LogProvider {

	public Log getLog(Class<?> clazz) {
		return new CustomLog();
	}

}
