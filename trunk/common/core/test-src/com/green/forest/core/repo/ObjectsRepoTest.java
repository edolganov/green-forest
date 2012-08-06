package com.green.forest.core.repo;

import java.util.HashSet;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.green.forest.api.exception.deploy.NoMappingAnnotationException;
import com.green.forest.api.test.action.StringAction;
import com.green.forest.core.test.model.handler.HandlerWithoutMapping;
import com.green.forest.core.test.model.handler.StringEcho;

public class ObjectsRepoTest extends Assert {
	
	
	@Test
	public void test_put() throws Exception{
		
		ObjectsRepo repo = new ObjectsRepo();
		repo.put(StringEcho.class);
		
		Map<Class<?>, HashSet<Class<?>>> map = repo.getInitalMapping();
		assertTrue(map.get(StringAction.class).contains(StringEcho.class));
		
	}
	
	
	@Test(expected=NoMappingAnnotationException.class)
	public void test_put_without_mapping() throws Exception{
		
		ObjectsRepo repo = new ObjectsRepo();
		repo.put(HandlerWithoutMapping.class);
		
	}

}

