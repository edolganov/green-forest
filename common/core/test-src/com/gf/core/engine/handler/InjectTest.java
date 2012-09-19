package com.gf.core.engine.handler;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.EngineTest;
import com.gf.core.engine.handler.model.StaticInjectHandler;
import com.gf.core.engine.handler.model.StaticServiceImpl;
import com.gf.test.action.EmptyAction;

@SuppressWarnings("unchecked")
public class InjectTest extends EngineTest {
	
	@Test
	public void test_inject_runtime_context_from_filter(){
		fail("todo");
	}
	
	
	@Test
	public void test_inject_from_duplicates(){
		fail("todo");
	}
	
	@Test
	public void test_inject_unknown(){
		fail("todo");
	}
	
	@Test
	public void test_inject(){
		
		Engine engine = new Engine();
		engine.addToContext(new StaticServiceImpl());
		engine.putHandler(StaticInjectHandler.class);
		
		engine.invoke(new EmptyAction());
		
	}

}
