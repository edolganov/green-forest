package com.green.forest.core.engine.handler;

import org.junit.Ignore;
import org.junit.Test;

import com.green.forest.api.exception.deploy.NoMappingAnnotationException;
import com.green.forest.api.exception.invoke.HandlerNotFoundException;
import com.green.forest.api.test.action.EmptyAction;
import com.green.forest.api.test.action.StringAction;
import com.green.forest.api.test.handler.HandlerWithoutMapping;
import com.green.forest.api.test.handler.StringEcho;
import com.green.forest.core.Engine;
import com.green.forest.core.engine.EngineTest;
import com.green.forest.core.engine.filter.model.BeginFilter;
import com.green.forest.core.engine.handler.model.SubInvoke;
import com.green.forest.core.engine.interceptor.model.BeginForAllByAnn;
import com.green.forest.core.engine.interceptor.model.FirstByAnn;
import com.green.forest.util.Util;

@SuppressWarnings("unchecked")
public class BasicTest extends EngineTest {
	
	
	@Ignore
	@Test
	public void test_inject(){
		
		fail("todo");
		
	}
	
	
	@Test
	public void test_sub_invoke(){
		
		Engine engine = new Engine();
		enableTracing(engine);
		
		engine.putHandler(SubInvoke.class);
		engine.putHandler(StringEcho.class);
		engine.putFilter(BeginFilter.class);
		engine.putInterceptor(BeginForAllByAnn.class);
		engine.putInterceptor(FirstByAnn.class);
		
		EmptyAction action = new EmptyAction();
		engine.invoke(action);
		
		checkTrace(action, 
				BeginFilter.class,
				BeginForAllByAnn.class,
				Util.list(SubInvoke.class,
						BeginForAllByAnn.class,
						FirstByAnn.class,
						StringEcho.class)
				);
		
	}
	
	
	
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
		enableTracing(engine);
		
		engine.putHandler(StringEcho.class);
		
		StringAction action = new StringAction("test");
		engine.invoke(action);
		
		checkTrace(action, 
				StringEcho.class);
		
		
	}

}
