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
		
		engine.scanAndPut("com.gf.core.engine.deploy");
		
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
