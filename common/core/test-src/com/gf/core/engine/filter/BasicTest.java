package com.gf.core.engine.filter;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.EngineTest;
import com.gf.core.engine.filter.model.BeginFilter;
import com.gf.core.engine.filter.model.EndFilter;
import com.gf.core.engine.filter.model.FirstFilter;
import com.gf.core.engine.filter.model.SecondFilter;
import com.gf.core.engine.filter.model.UnorderedFilter;
import com.gf.test.action.StringAction;
import com.gf.test.handler.StringEcho;
import com.gf.test.interceptor.StringReverse;

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
