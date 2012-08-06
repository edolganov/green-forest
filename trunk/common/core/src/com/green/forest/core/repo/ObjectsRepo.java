package com.green.forest.core.repo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.green.forest.api.annotation.Mapping;
import com.green.forest.api.exception.deploy.DeployException;
import com.green.forest.api.exception.deploy.NoMappingAnnotationException;
import com.green.forest.util.Util;

public class ObjectsRepo {
	
	private static Log log = LogFactory.getLog(ObjectsRepo.class);
	
	private HashMap<Class<?>, HashSet<Class<?>>> initialMapping = new HashMap<Class<?>, HashSet<Class<?>>>();
	
	
	private ReadWriteLock rw = new ReentrantReadWriteLock();
	private Lock readLock = rw.readLock();
	private Lock writeLock = rw.writeLock();
	
	public void put(Class<?> clazz) throws DeployException{
		writeLock.lock();
		try {
			
			Mapping annotation = clazz.getAnnotation(Mapping.class);
			Util.checkEmpty(annotation, NoMappingAnnotationException.class);
			
			Class<?>[] types = annotation.value();
			for(Class<?> type : types){
				putToMapping(type, clazz);
			}
		}finally {
			writeLock.unlock();
		}
	}

	private void putToMapping(Class<?> type, Class<?> clazz) {
		HashSet<Class<?>> set = initialMapping.get(type);
		if(set == null){
			set = new HashSet<Class<?>>();
			initialMapping.put(type, set);
		}
		if(set.contains(clazz)){
			log.warn("mapping already contains "+clazz+" for "+type);
		} else {
			set.add(clazz);
		}
	}
	
	Map<Class<?>, HashSet<Class<?>>> getInitalMapping(){
		readLock.lock();
		try {
			return new HashMap<Class<?>, HashSet<Class<?>>>(initialMapping);
		} finally {
			readLock.unlock();
		}
	}

}
