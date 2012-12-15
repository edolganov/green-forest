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
