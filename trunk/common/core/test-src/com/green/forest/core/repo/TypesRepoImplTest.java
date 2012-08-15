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
		repo.put(TestHandler_superclass.class);
		repo.put(TestHandler_interface.class);
		repo.put(TestHandler_final_class.class);
		repo.put(TestHandler_object.class);
		
		containsOnly(repo.getTypes(TestTarget_superclass.class), 
				TestHandler_superclass.class, 
				TestHandler_object.class);
		
		containsOnly(repo.getTypes(TestTarget_interface.class), 
				TestHandler_interface.class, 
				TestHandler_object.class);
		
		containsOnly(repo.getTypes(TestTarget_final_class.class), 
				TestHandler_superclass.class, 
				TestHandler_interface.class, 
				TestHandler_final_class.class, 
				TestHandler_object.class);
		
		
	}
	
	private void containsOnly(Set<Class<?>> types, Class<?>... classes){
		if( ! Util.isEmpty(classes)){
			assertEquals("types: "+types, classes.length, types.size());
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

class TestTarget_superclass {}

interface TestTarget_interface {}

class TestTarget_final_class extends TestTarget_superclass implements TestTarget_interface {}


@Mapping(TestTarget_superclass.class)
class TestHandler_superclass {}

@Mapping(TestTarget_interface.class)
class TestHandler_interface {}

@Mapping(TestTarget_final_class.class)
class TestHandler_final_class {}

@Mapping(Object.class)
class TestHandler_object {}


