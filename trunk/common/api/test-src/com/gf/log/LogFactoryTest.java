package com.gf.log;

import org.junit.Test;

import com.gf.util.junit.AssertExt;

public class LogFactoryTest extends AssertExt {
	
	@Test
	public void test_same_of_instances(){
		
		Log log1 = LogFactory.getLog(LogFactoryTest.class);
		Log log2 = LogFactory.getLog(LogFactoryTest.class);
		
		assertSame(log1, log2);
	}

}
