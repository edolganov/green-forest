package com.green.forest.core.repo;

import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import com.green.forest.api.annotation.Mapping;
import com.green.forest.api.exception.deploy.NoMappingAnnotationException;
import com.green.forest.api.exception.deploy.NotOneHandlerException;
import com.green.forest.api.test.action.StringAction;
import com.green.forest.api.test.handler.HandlerWithoutMapping;
import com.green.forest.api.test.handler.StringEcho;
import com.green.forest.util.Util;

public abstract class AbstractTypesRepoTest extends Assert {
	
	protected abstract TypesRepo getRepo();
	
	
	@Test
	public void test_reload_cache_after_put(){
		
		TypesRepo repo = getRepo();
		
		repo.put(TestHandler_superclass.class);
		Set<Class<?>> set1 = repo.getTypes(TestTarget_superclass.class);
		containsOnly(set1, TestHandler_superclass.class);
		
		repo.put(TestHandler_object.class);
		Set<Class<?>> set2 = repo.getTypes(TestTarget_superclass.class);
		containsOnly(set2, TestHandler_superclass.class, TestHandler_object.class);
		
		
		
		
	}
	
	@Test
	public void test_change_one_handler_flag(){
		
		TypesRepo repo = getRepo();
		
		repo.setOneHandlerOnly(true);
		repo.put(TestHandler_superclass.class);
		
		try {
			repo.put(TestHandler_object.class);	
			fail("need "+NotOneHandlerException.class);
		}catch (NotOneHandlerException e) {
			//ok
		}
		
		repo.setOneHandlerOnly(false);
		repo.put(TestHandler_object.class);
		
		try {
			repo.setOneHandlerOnly(true);
			fail("need "+NotOneHandlerException.class);
		} catch (NotOneHandlerException e) {
			//ok
		}
		
		
		
	}
	
	
	@Test
	public void test_rollback_after_many_handlers_exception(){
		
		TypesRepo repo = getRepo();
		
		repo.setOneHandlerOnly(true);
		repo.put(TestHandler_superclass.class);
		
		try {
			repo.put(TestHandler_object.class);	
			fail("need "+NotOneHandlerException.class);
		}catch (NotOneHandlerException e) {
			//ok
		}
		
		Set<Class<?>> types = repo.getTypes(TestTarget_superclass.class);
		containsOnly(types, TestHandler_superclass.class);
		
	}
	
	
	@Test(expected=NotOneHandlerException.class)
	public void test_one_handler_only_true(){
		
		TypesRepo repo = getRepo();
		repo.setOneHandlerOnly(true);
		
		assertTrue(repo.isOneHandlerOnly());
		
		repo.put(TestHandler_superclass.class);
		repo.put(TestHandler_object.class);
		
	}
	
	
	@Test
	public void test_get(){
		
		TypesRepo repo = getRepo();
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
	
	
	@Test
	public void test_put() throws Exception{
		
		TypesRepo repo = getRepo();
		repo.put(StringEcho.class);
		
		containsOnly(repo.getTypes(StringAction.class), StringEcho.class);
		
	}
	
	
	@Test(expected=NoMappingAnnotationException.class)
	public void test_put_without_mapping() throws Exception{
		
		TypesRepo repo = getRepo();
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


