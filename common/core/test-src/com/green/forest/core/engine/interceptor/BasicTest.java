package com.green.forest.core.engine.interceptor;

import junit.framework.Assert;

import org.junit.Test;

import com.green.forest.api.test.action.StringAction;
import com.green.forest.api.test.handler.StringEcho;
import com.green.forest.api.test.interceptor.StringReverse;
import com.green.forest.core.Engine;
import com.green.forest.core.engine.interceptor.model.FirstByAnn;
import com.green.forest.core.engine.interceptor.model.SecondByAnn;
import com.green.forest.core.engine.interceptor.model.ThirdByAnn;

public class BasicTest extends Assert {
	
	
	@Test
	public void test_order_by_annotation() throws Exception {
		
		Engine engine = new Engine();
		engine.putHandler(StringEcho.class);
		engine.putInterceptor(ThirdByAnn.class);
		engine.putInterceptor(FirstByAnn.class);
		engine.putInterceptor(SecondByAnn.class);
		
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
