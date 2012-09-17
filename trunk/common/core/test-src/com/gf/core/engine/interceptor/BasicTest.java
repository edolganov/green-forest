package com.gf.core.engine.interceptor;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.EngineTest;
import com.gf.core.engine.interceptor.model.BeginForAllByAnn;
import com.gf.core.engine.interceptor.model.EndForAllByAnn;
import com.gf.core.engine.interceptor.model.FirstByAnn;
import com.gf.core.engine.interceptor.model.SecondByAnn;
import com.gf.test.action.StringAction;
import com.gf.test.handler.StringEcho;
import com.gf.test.interceptor.StringReverse;

public class BasicTest extends EngineTest {
	
	
	@Test
	public void test_order_by_annotation() throws Exception {
		
		Engine engine = new Engine();
		enableTracing(engine);
		
		engine.putHandler(StringEcho.class);
		engine.putInterceptor(FirstByAnn.class);
		engine.putInterceptor(SecondByAnn.class);
		engine.putInterceptor(BeginForAllByAnn.class);
		engine.putInterceptor(EndForAllByAnn.class);
		engine.putInterceptor(StringReverse.class);
		
		StringAction action = new StringAction("test");
		engine.invoke(action);
		
		checkTrace(action, 
				BeginForAllByAnn.class,
				FirstByAnn.class,
				SecondByAnn.class,
				StringReverse.class,
				EndForAllByAnn.class,
				StringEcho.class);
	}
	
	
	@Test
	public void test_invoke(){
		
		Engine engine = new Engine();
		enableTracing(engine);
		
		engine.putHandler(StringEcho.class);
		engine.putInterceptor(StringReverse.class);
		
		StringAction action = new StringAction("test");
		engine.invoke(action);
		
		checkTrace(action, 
				StringReverse.class,
				StringEcho.class);
		
	}

}
