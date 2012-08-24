package com.green.forest.core;

import junit.framework.Assert;

import org.junit.Test;

import com.green.forest.api.test.action.StringAction;
import com.green.forest.api.test.handler.StringEcho;

public class EngineTest extends Assert {
	
	@Test
	public void test_invoke(){
		
		Engine engine = new Engine();
		engine.putHandler(StringEcho.class);
		
		String param = "test";
		String result = engine.invoke(new StringAction(param));
		assertEquals(param, result);
		
	}

}
