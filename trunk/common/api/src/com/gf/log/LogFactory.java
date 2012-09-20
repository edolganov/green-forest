package com.gf.log;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.gf.exception.ExternalException;

public class LogFactory {
	
	private static final String SLF4J_LOG_PROVIDER = "com.gf.components.log.slf4j.Slf4jLogProvider";
	private static final String APACHE_LOG_PROVIDER = "com.gf.components.log.apache.ApacheLogProvider";
	
	private static final LogProvider provider = findProvider();
	
	private static HashMap<Class<?>, Log> cache = new HashMap<Class<?>, Log>();
	private static ReadWriteLock rw = new ReentrantReadWriteLock();
	private static Lock readLock = rw.readLock();
	private static Lock writeLock = rw.writeLock();
	
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
		
		Log log = getFromCache(clazz);
		if(log == null){
			log = provider.getLog(clazz);
			putToCache(clazz, log);
		}
		
		return log;
	}

	private static void putToCache(Class<?> clazz, Log log) {
		writeLock.lock();
		try {
			cache.put(clazz, log);
		} finally {
			writeLock.unlock();
		}
	}

	private static Log getFromCache(Class<?> clazz) {
		readLock.lock();
		try {
			return cache.get(clazz);
		}finally {
			readLock.unlock();
		}
	}



}
