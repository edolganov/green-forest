package com.gf.log;

import org.junit.Test;

import com.gf.util.junit.AssertExt;

public class CustomLogProviderTest extends AssertExt {
	
	static {
		System.setProperty("com.gf.log.LogProvider", "com.gf.log.CustomLogProvider");
		LogFactory.reFindProvider();
	}
	
	@Test
	public void test_get_custom(){
		
		Log log = LogFactory.getLog(getClass());
		assertEquals(CustomLog.class, log.getClass());
	}

}
