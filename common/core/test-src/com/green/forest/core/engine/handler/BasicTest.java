package com.green.forest.core.engine.handler;

import junit.framework.Assert;

import org.junit.Test;

import com.green.forest.api.exception.deploy.NoMappingAnnotationException;
import com.green.forest.api.exception.invoke.HandlerNotFoundException;
import com.green.forest.api.test.action.StringAction;
import com.green.forest.api.test.handler.HandlerWithoutMapping;
import com.green.forest.api.test.handler.StringEcho;
import com.green.forest.core.Engine;

public class BasicTest extends Assert {
	
	
	
	
	@Test(expected=NoMappingAnnotationException.class)
	public void test_no_mapping(){
		
		Engine engine = new Engine();
		engine.putHandler(HandlerWithoutMapping.class);
	}
	


	@Test(expected=HandlerNotFoundException.class)
	public void test_no_handler(){
		
		Engine engine = new Engine();
		String param = "test";
		engine.invoke(new StringAction(param));
		
	}
	
	@Test
	public void test_invoke(){
		
		Engine engine = new Engine();
		engine.putHandler(StringEcho.class);
		
		String param = "test";
		String result = engine.invoke(new StringAction(param));
		assertEquals(param, result);
		
	}

}
