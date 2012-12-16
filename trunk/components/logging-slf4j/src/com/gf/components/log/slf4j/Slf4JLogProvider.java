package com.gf.components.log.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gf.log.Log;
import com.gf.log.LogProvider;

public class Slf4JLogProvider implements LogProvider {

	@Override
	public Log getLog(Class<?> clazz) {
		Logger logger = LoggerFactory.getLogger(clazz);
		return new LogImpl(logger);
	}

}
