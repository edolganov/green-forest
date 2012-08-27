package com.green.forest.core.engine.filter;

import org.junit.Test;

import com.green.forest.api.test.action.StringAction;
import com.green.forest.api.test.handler.StringEcho;
import com.green.forest.api.test.interceptor.StringReverse;
import com.green.forest.core.Engine;
import com.green.forest.core.engine.EngineTest;
import com.green.forest.core.engine.filter.model.FirstFilter;

public class BasicTest extends EngineTest {
	
	@Test
	public void test_invoke(){
		
		Engine engine = new Engine();
		enableTracing(engine);
		
		engine.putHandler(StringEcho.class);
		engine.putFilter(FirstFilter.class);
		engine.putInterceptor(StringReverse.class);
		
		StringAction action = new StringAction("test");
		engine.invoke(action);
		
		checkTrace(action,
				FirstFilter.class,
				StringReverse.class,
				StringEcho.class);
		
	}
	

}
