package com.green.forest.core.engine.filter;

import org.junit.Test;

import com.green.forest.api.test.action.StringAction;
import com.green.forest.api.test.handler.StringEcho;
import com.green.forest.api.test.interceptor.StringReverse;
import com.green.forest.core.Engine;
import com.green.forest.core.engine.EngineTest;
import com.green.forest.core.engine.filter.model.BeginFilter;
import com.green.forest.core.engine.filter.model.EndFilter;
import com.green.forest.core.engine.filter.model.FirstFilter;
import com.green.forest.core.engine.filter.model.SecondFilter;
import com.green.forest.core.engine.filter.model.UnorderedFilter;

public class BasicTest extends EngineTest {
	
	@Test
	public void test_order_by_annotation(){
		
		Engine engine = new Engine();
		enableTracing(engine);
		
		engine.putHandler(StringEcho.class);
		engine.putFilter(FirstFilter.class);
		engine.putFilter(SecondFilter.class);
		engine.putFilter(BeginFilter.class);
		engine.putFilter(EndFilter.class);
		engine.putFilter(UnorderedFilter.class);
		
		StringAction action = new StringAction("test");
		engine.invoke(action);
		
		checkTrace(action,
				BeginFilter.class,
				FirstFilter.class,
				SecondFilter.class,
				UnorderedFilter.class,
				EndFilter.class,
				StringEcho.class);
	}
	
	
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
