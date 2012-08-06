package com.green.forest.util.log;

import java.util.*;
import java.util.concurrent.*;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.junit.Test;

public class LogRepoTest extends Assert {
	
	@Test
	public void test_multi_get() throws Exception{
		
		int threadsCount = 10;
		int tasksCount = 20;

		ArrayList<Callable<Log>> tasks = new ArrayList<Callable<Log>>();
		for(int i=0; i < tasksCount; i++){
			tasks.add(new Callable<Log>() {
				
				@Override
				public Log call() throws Exception {
					return LogRepo.getLog(LogRepoTest.class);
				}
			});
		}
		
		ExecutorService executorService = Executors.newFixedThreadPool(threadsCount);
		List<Future<Log>> list = executorService.invokeAll(tasks);
		for(Future<Log> f : list){
			f.get();
		}
		executorService.shutdown();
		
		assertEquals(1, LogRepo.getCreatedCount());
	}

}
