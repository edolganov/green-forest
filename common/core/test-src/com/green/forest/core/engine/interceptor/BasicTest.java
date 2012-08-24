package com.green.forest.core.engine.interceptor;

import junit.framework.Assert;

import org.junit.Test;

import com.green.forest.api.test.action.StringAction;
import com.green.forest.api.test.handler.StringEcho;
import com.green.forest.api.test.interceptor.StringReverse;
import com.green.forest.core.Engine;

public class BasicTest extends Assert {
	
	@Test
	public void test_invoke(){
		
		Engine engine = new Engine();
		engine.putHandler(StringEcho.class);
		engine.putInterceptor(StringReverse.class);
		
		String param = "123";
		String expected = new StringBuilder(param).reverse().toString();
		String result = engine.invoke(new StringAction(param));
		assertEquals(expected, result);
		
	}

}
