package com.green.forest.util.log;

import java.util.HashMap;
import java.util.concurrent.locks.*;

import org.apache.commons.logging.*;

public class LogRepo {
	
	private static HashMap<Class<?>, Log> map = new HashMap<Class<?>, Log>();
	private static ReadWriteLock rw = new ReentrantReadWriteLock();
	private static Lock readLock = rw.readLock();
	private static Lock writeLock = rw.writeLock();
	
	private static int createdCount = 0;
	
	
	public static Log getLog(Class<?> clazz){
		
		readLock.lock();
		if( ! map.containsKey(clazz)){
			readLock.unlock();
			writeLock.lock();
			try {
				if( ! map.containsKey(clazz)){
					Log clazzLog = LogFactory.getLog(clazz);
					map.put(clazz, clazzLog);
					createdCount++;
				}
				readLock.lock();
			} finally {
				writeLock.unlock();
			}
		}
		
		try {
			Log out = map.get(clazz);
			return out;
		} finally {
			readLock.unlock();
		}

	}
	
	static int getCreatedCount(){
		readLock.lock();
		try {
			return createdCount;
		} finally {
			readLock.unlock();
		}
	}

}
