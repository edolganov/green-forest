package com.gf.log;

import com.gf.exception.ExternalException;

public class LogFactory {
	
	private static final String SLF4J_LOG_PROVIDER = "com.gf.components.log.slf4j.Slf4jLogProvider";
	private static final String APACHE_LOG_PROVIDER = "com.gf.components.log.apache.ApacheLogProvider";
	
	private static final LogProvider provider = findProvider();
	
	private static LogProvider findProvider() {
		
		Class<?> providerClass = findProviderClass();
		try {
			return (LogProvider)providerClass.newInstance();
		}catch (Exception e) {
			throw new ExternalException("can't create log provider", e);
		}
	}

	private static Class<?> findProviderClass() {
		Class<?> providerClass = null;
		
		if(providerClass == null){
			try {
				providerClass = Class.forName(APACHE_LOG_PROVIDER);
			}catch (Exception e) {
				//no apache-commons-log
			}
		}
		
		if(providerClass == null){
			try {
				providerClass = Class.forName(SLF4J_LOG_PROVIDER);
			}catch (Exception e) {
				//no slf4j
			}
		}
		
		if(providerClass == null){
			providerClass = DefaultLogProvider.class;
		}
		
		return providerClass;
	}
	
	

	public static final Log getLog(Class<?> clazz) {
		return provider.getLog(clazz);
	}



}
