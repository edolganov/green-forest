package com.gf.lang;

import org.junit.Test;

import com.gf.util.junit.AssertExt;

public class LangTest extends AssertExt {
	
	
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
