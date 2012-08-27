package com.green.forest.core.engine.interceptor;

import org.junit.Test;

import com.green.forest.api.test.action.StringAction;
import com.green.forest.api.test.handler.StringEcho;
import com.green.forest.api.test.interceptor.StringReverse;
import com.green.forest.core.Engine;
import com.green.forest.core.engine.EngineTestUtil;
import com.green.forest.core.engine.interceptor.model.BeginForAllByAnn;
import com.green.forest.core.engine.interceptor.model.EndForAllByAnn;
import com.green.forest.core.engine.interceptor.model.FirstByAnn;
import com.green.forest.core.engine.interceptor.model.SecondByAnn;
import com.green.forest.core.engine.interceptor.model.ThirdByAnn;

public class BasicTest extends EngineTestUtil {
	
	
	@Test
	public void test_order_by_annotation() throws Exception {
		
		Engine engine = new Engine();
		prepareTrace(engine);
		
		engine.putHandler(StringEcho.class);
		engine.putInterceptor(FirstByAnn.class);
		engine.putInterceptor(SecondByAnn.class);
		engine.putInterceptor(ThirdByAnn.class);
		engine.putInterceptor(BeginForAllByAnn.class);
		engine.putInterceptor(EndForAllByAnn.class);
		engine.putInterceptor(StringReverse.class);
		
		StringAction action = new StringAction("test");
		engine.invoke(action);
		
		checkTrace(action, 
				BeginForAllByAnn.class,
				FirstByAnn.class,
				SecondByAnn.class,
				ThirdByAnn.class,
				StringReverse.class,
				EndForAllByAnn.class,
				StringEcho.class);
	}
	
	
	@Test
	public void test_invoke(){
		
		Engine engine = new Engine();
		prepareTrace(engine);
		
		engine.putHandler(StringEcho.class);
		engine.putInterceptor(StringReverse.class);
		
		StringAction action = new StringAction("test");
		engine.invoke(action);
		
		checkTrace(action, 
				StringReverse.class,
				StringEcho.class);
		
	}

}
