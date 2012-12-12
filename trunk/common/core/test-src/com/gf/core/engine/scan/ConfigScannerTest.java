package com.gf.core.engine.scan;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.AbstractEngineTest;
import com.gf.key.scan.ClassScannerKey;

public class ConfigScannerTest extends AbstractEngineTest {
	
	@Test
	public void test_set_config(){
		
		Engine engine = new Engine();
		engine.setConfig(ClassScannerKey.class, TestClassScanner.class);
		engine.scanForAnnotations(this.getClass());
		
		int instances = TestClassScanner.instanceCounter.get();
		assertEquals(1, instances);
		
	}

}
