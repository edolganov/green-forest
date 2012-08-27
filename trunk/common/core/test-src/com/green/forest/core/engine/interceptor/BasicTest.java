package com.green.forest.core.engine.interceptor;

import junit.framework.Assert;

import org.junit.Test;

import com.green.forest.api.test.action.StringAction;
import com.green.forest.api.test.handler.StringEcho;
import com.green.forest.api.test.interceptor.StringReverse;
import com.green.forest.core.Engine;
import com.green.forest.core.engine.interceptor.model.First;
import com.green.forest.core.engine.interceptor.model.Second;
import com.green.forest.core.engine.interceptor.model.Third;

public class BasicTest extends Assert {
	
	
	@Test
	public void test_interceptors_order() throws Exception {
		
		Engine engine = new Engine();
		engine.putHandler(StringEcho.class);
		engine.putInterceptor(Third.class);
		engine.putInterceptor(First.class);
		engine.putInterceptor(Second.class);
		
		String param = "123";
		
		String expected = param+"-first-second-third";
		
		String result = engine.invoke(new StringAction(param));
		assertEquals(expected, result);
	}
	
	
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
