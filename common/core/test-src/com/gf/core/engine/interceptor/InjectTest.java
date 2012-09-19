package com.gf.core.engine.interceptor;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.AbstractEngineTest;
import com.gf.core.engine.filter.model.InvocationContextSetterFilter;
import com.gf.core.engine.interceptor.model.InjectAllInterceptor;
import com.gf.core.engine.interceptor.model.InjectUnknownInterceptor;
import com.gf.core.engine.interceptor.model.InjectStaticInterceptor;
import com.gf.core.engine.model.StaticServiceImpl;
import com.gf.core.engine.model.StaticServiceImpl2;
import com.gf.exception.invoke.ObjectToInjectNotFoundException;
import com.gf.test.action.EmptyAction;
import com.gf.test.handler.EmptyHandler;


public class InjectTest extends AbstractEngineTest {
	
	@Test
	public void test_inject_invocation_context_from_filter(){
		
		Engine engine = new Engine();
		engine.addToContext(new StaticServiceImpl());
		engine.putFilter(InvocationContextSetterFilter.class);
		engine.putInterceptor(InjectAllInterceptor.class);
		engine.putHandler(EmptyHandler.class);
		
		engine.invoke(new EmptyAction());	
	}
	
	
	@Test
	public void test_inject_from_duplicates(){
		
		Engine engine = new Engine();
		engine.addToContext(new StaticServiceImpl());
		engine.addToContext(new StaticServiceImpl2());
		engine.putInterceptor(InjectStaticInterceptor.class);
		engine.putHandler(EmptyHandler.class);
		
		engine.invoke(new EmptyAction());
		
	}
	
	@Test(expected=ObjectToInjectNotFoundException.class)
	public void test_inject_unknown(){
		
		Engine engine = new Engine();
		engine.putInterceptor(InjectUnknownInterceptor.class);
		engine.putHandler(EmptyHandler.class);
		
		engine.invoke(new EmptyAction());
		
	}
	
	@Test
	public void test_inject(){
		
		Engine engine = new Engine();
		engine.addToContext(new StaticServiceImpl());
		engine.putInterceptor(InjectStaticInterceptor.class);
		engine.putHandler(EmptyHandler.class);
		
		engine.invoke(new EmptyAction());
		
	}
	

}
