package com.green.forest.core.repo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.green.forest.api.annotation.Mapping;
import com.green.forest.api.exception.deploy.DeployException;
import com.green.forest.api.exception.deploy.NoMappingAnnotationException;
import com.green.forest.util.Util;

public class TypesRepoImpl implements TypesRepo {
	
	private static Log log = LogFactory.getLog(TypesRepoImpl.class);
	
	private HashMap<Class<?>, Set<Class<?>>> initialMapping = new HashMap<Class<?>, Set<Class<?>>>();
	private HashMap<Class<?>, Set<Class<?>>> targetCache = new HashMap<Class<?>, Set<Class<?>>>();
	
	
	private ReadWriteLock rw = new ReentrantReadWriteLock();
	private Lock readLock = rw.readLock();
	private Lock writeLock = rw.writeLock();
	
	@Override
	public void put(Class<?> handler) throws DeployException {
		writeLock.lock();
		try {
			
			Mapping annotation = handler.getAnnotation(Mapping.class);
			Util.checkEmpty(annotation, NoMappingAnnotationException.class);
			
			Class<?>[] types = annotation.value();
			for(Class<?> type : types){
				putToMapping(type, handler);
				
			}
		}finally {
			writeLock.unlock();
		}
	}
	
	
	@Override
	public Set<Class<?>> getTypes(Class<?> target) {
		
		//check-create
		readLock.lock();
		if ( ! hasInTargetCache(target)) {
			readLock.unlock();
			writeLock.lock();
		    try {
		    	if ( ! hasInTargetCache(target)) {
		    		putToTargetCache(target);
		    	}
		    	readLock.lock();
		    } finally {
		    	writeLock.unlock();
		    }
		}

	    //read
		try {
			Set<Class<?>> out = getFromTargetCache(target);
			return out;
		} finally {
			 readLock.unlock();
		}
	}

	
	@Deprecated
	Map<Class<?>, Set<Class<?>>> getInitalMapping(){
		readLock.lock();
		try {
			return new HashMap<Class<?>, Set<Class<?>>>(initialMapping);
		} finally {
			readLock.unlock();
		}
	}
	
	@Deprecated
	Map<Class<?>, Set<Class<?>>> getTargetCache(){
		readLock.lock();
		try {
			return new HashMap<Class<?>, Set<Class<?>>>(targetCache);
		} finally {
			readLock.unlock();
		}
	}
	



	private boolean hasInTargetCache(Class<?> target) {
		return targetCache.containsKey(target);
	}
	
	private void putToTargetCache(Class<?> target) {
		
		HashSet<Class<?>> allHandlers = new HashSet<Class<?>>();
		
		LinkedList<Class<?>> queue = new LinkedList<Class<?>>();
		queue.addLast(target);
		
		while( ! queue.isEmpty()){
			Class<?> curTarget = queue.removeFirst();
			Set<Class<?>> curHandlersTypes = initialMapping.get(curTarget);
			if( ! Util.isEmpty(curHandlersTypes)){
				allHandlers.addAll(curHandlersTypes);
			}
			
			Class<?> superclass = curTarget.getSuperclass();
			if( ! Util.isEmpty(superclass)){
				queue.addLast(superclass);
			}
			
			Class<?>[] interfaces = curTarget.getInterfaces();
			if( ! Util.isEmpty(interfaces)){
				for(Class<?> interfaceType : interfaces){
					queue.addLast(interfaceType);
				}
			}
		}
		
		targetCache.put(target, allHandlers);
		
	}
	
	private Set<Class<?>> getFromTargetCache(Class<?> target) {
		Set<Class<?>> original = targetCache.get(target);
		if(original == null){
			original = new HashSet<Class<?>>();
		}
		HashSet<Class<?>> copy = new HashSet<Class<?>>(original);
		return copy;
	}


	private void putToMapping(Class<?> type, Class<?> clazz) {
		Set<Class<?>> set = initialMapping.get(type);
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
	



}
