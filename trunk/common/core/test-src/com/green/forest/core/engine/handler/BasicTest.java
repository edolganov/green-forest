package com.green.forest.core.engine.handler;

import org.junit.Test;

import com.green.forest.api.exception.deploy.NoMappingAnnotationException;
import com.green.forest.api.exception.invoke.HandlerNotFoundException;
import com.green.forest.api.test.action.StringAction;
import com.green.forest.api.test.handler.HandlerWithoutMapping;
import com.green.forest.api.test.handler.StringEcho;
import com.green.forest.core.Engine;
import com.green.forest.core.engine.EngineTestUtil;

public class BasicTest extends EngineTestUtil {
	
	
	
	
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
		prepareTrace(engine);
		
		engine.putHandler(StringEcho.class);
		
		StringAction action = new StringAction("test");
		engine.invoke(action);
		
		checkTrace(action, 
				StringEcho.class);
		
		
	}

}
