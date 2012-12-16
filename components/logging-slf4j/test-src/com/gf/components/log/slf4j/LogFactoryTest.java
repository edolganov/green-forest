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
package com.gf.components.log.slf4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gf.util.junit.AssertExt;

public class LogFactoryTest extends AssertExt {
	
	@Test
	public void test_same_of_instances(){
		
		Logger log1 = LoggerFactory.getLogger(LogFactoryTest.class);
		Logger log2 = LoggerFactory.getLogger(LogFactoryTest.class);
		
		assertSame(log1, log2);
	}

}
