package com.gf.components.log.apache;

import org.apache.commons.logging.LogFactory;

import com.gf.log.Log;
import com.gf.log.LogProvider;

public class ApacheLogProvider implements LogProvider {

	@Override
	public Log getLog(Class<?> clazz) {
		org.apache.commons.logging.Log original = LogFactory.getLog(clazz);
		return new LogImpl(original);
	}

}
