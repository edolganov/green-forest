package com.gf.core.engine.deploy;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.AbstractEngineTest;
import com.gf.core.engine.deploy.scan.ScanFilter;
import com.gf.core.engine.deploy.scan.ScanHandler;
import com.gf.core.engine.deploy.scan.ScanInterceptor;
import com.gf.core.engine.deploy.scan.subpackage.SubScanFilter;
import com.gf.core.engine.deploy.scan.subpackage.SubScanInterceptor;
import com.gf.test.action.EmptyAction;

public class DeployTest extends AbstractEngineTest {
	
	@Test
	public void test_scan_package(){
		
		Engine engine = new Engine();
		enableTracing(engine);
		
		engine.scanForAnnotations("com.gf.core.engine.deploy");
		
		EmptyAction action = new EmptyAction();
		engine.invoke(action);
		checkTrace(action, 
				SubScanFilter.class,
				ScanFilter.class,
				SubScanInterceptor.class,
				ScanInterceptor.class,
				ScanHandler.class
				);
		
	}

}
