package com.gf.components.reflections;

import java.util.Set;

import org.junit.Test;

import com.gf.InvocationObject;
import com.gf.components.reflections.scan.TestA;
import com.gf.components.reflections.scan.subpackage.TestB;
import com.gf.components.reflections.scan.subpackage.TestC;
import com.gf.util.junit.AssertExt;

public class ReflectionsScanTest extends AssertExt {
	
	@Test
	public void test_invoke(){
		
		String packageRoot = ReflectionsScanTest.class.getPackage().getName();
		Set<Class<?>> set = new ReflectionsScanner().getClasses(packageRoot, InvocationObject.class);
		assertEquals(3, set.size());
		assertTrue(set.contains(TestA.class));
		assertTrue(set.contains(TestB.class));
		assertTrue(set.contains(TestC.class));
		
	}

}
