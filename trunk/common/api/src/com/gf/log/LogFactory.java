/*
 * Copyright 2012 Evgeny Dolganov (evgenij.dolganov@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gf.log;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.gf.exception.InvalidStateException;
import com.gf.extra.components.ComponentChecker;

public class LogFactory {
	
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
			throw new InvalidStateException("can't create log provider", e);
		}
	}

	private static Class<?> findProviderClass() {
		Class<?> providerClass = null;
		
		if(providerClass == null){
			providerClass = ComponentChecker.findAndCheckBy("com.gf.components.log.apache.LogCheckerImpl");
		}
		
		if(providerClass == null){
			providerClass = ComponentChecker.findAndCheckBy("com.gf.components.log.slf4j.LogCheckerImpl");
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
