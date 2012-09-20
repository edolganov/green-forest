package com.gf.log;

import junit.framework.Assert;

import org.junit.Test;

public class LogFactoryTest extends Assert {
	
	@Test
	public void test_same_of_instances(){
		
		Log log1 = LogFactory.getLog(LogFactoryTest.class);
		Log log2 = LogFactory.getLog(LogFactoryTest.class);
		
		assertSame(log1, log2);
	}

}
