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
package com.gf.components.log.apache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
