package com.gf.log;

import java.util.logging.Logger;

public class DefaultLogProvider implements LogProvider {

	@Override
	public Log getLog(Class<?> clazz) {
		Logger original = Logger.getLogger(clazz.getName());
		return new DefaultLogImpl(original);
	}

}
