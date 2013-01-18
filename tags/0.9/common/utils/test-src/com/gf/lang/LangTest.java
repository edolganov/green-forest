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
package com.gf.lang;

import org.junit.Test;

import com.gf.util.junit.AssertExt;

public class LangTest extends AssertExt {
	
	@Test
	public void test_parentClass_of_Object(){
		assertEquals(null, Object.class.getSuperclass());
	}
	
	
	@Test
	public void test_class_of_class(){
		
		assertEquals(Class.class, String.class.getClass());
		
	}
	
	
	@Test
	public void test_get_superclass_from_interface(){
		
		assertNull(LangTest_A.class.getSuperclass());
		assertNull(LangTest_B.class.getSuperclass());
		assertNull(LangTest_C.class.getSuperclass());
		
		assertEquals(LangTest_A.class, LangTest_C.class.getInterfaces()[0]);
		assertEquals(LangTest_B.class, LangTest_C.class.getInterfaces()[1]);
		assertEquals(0, LangTest_A.class.getInterfaces().length);
	}
	
	
	@Test
	public void test_get_object_superclass(){
		
		assertNull(Object.class.getSuperclass());
		
	}

}



interface LangTest_A {
	
}

interface LangTest_B {
	
}

interface LangTest_C extends LangTest_A, LangTest_B  {
	
}
