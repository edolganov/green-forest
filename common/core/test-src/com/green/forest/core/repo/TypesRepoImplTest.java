package com.green.forest.core.repo;

import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import com.green.forest.api.annotation.Mapping;
import com.green.forest.api.exception.deploy.NoMappingAnnotationException;
import com.green.forest.api.test.action.StringAction;
import com.green.forest.core.test.model.handler.HandlerWithoutMapping;
import com.green.forest.core.test.model.handler.StringEcho;
import com.green.forest.util.Util;

public class TypesRepoImplTest extends Assert {
	
	
	@Test
	public void test_get(){
		
		TypesRepoImpl repo = new TypesRepoImpl();
		repo.put(TestHandler_A.class);
		repo.put(TestHandler_B.class);
		repo.put(TestHandler_C.class);
		repo.put(TestHandler_All.class);
		
		testContainsOnly(repo.getTypes(TestTarget_A.class), TestHandler_A.class, TestHandler_All.class);
		testContainsOnly(repo.getTypes(TestTarget_B.class), TestHandler_B.class, TestHandler_C.class, TestHandler_All.class);
		
		
	}
	
	private void testContainsOnly(Set<Class<?>> types, Class<?>... classes){
		if( ! Util.isEmpty(classes)){
			assertEquals(classes.length, types.size());
			for(Class<?> clazz : classes){
				assertTrue("type "+clazz, types.contains(clazz));
			}
		}
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void test_put() throws Exception{
		
		TypesRepoImpl repo = new TypesRepoImpl();
		repo.put(StringEcho.class);
		
		Map<Class<?>, Set<Class<?>>> map = repo.getInitalMapping();
		assertTrue(map.get(StringAction.class).contains(StringEcho.class));
		
	}
	
	
	@Test(expected=NoMappingAnnotationException.class)
	public void test_put_without_mapping() throws Exception{
		
		TypesRepoImpl repo = new TypesRepoImpl();
		repo.put(HandlerWithoutMapping.class);
		
	}

}

class TestTarget_A {}

interface TestTarget_B {}

class TestTarget_C extends TestTarget_A implements TestTarget_B {}


@Mapping(TestTarget_A.class)
class TestHandler_A {}

@Mapping(TestTarget_B.class)
class TestHandler_B {}

@Mapping(TestTarget_C.class)
class TestHandler_C {}

@Mapping(Object.class)
class TestHandler_All {}


