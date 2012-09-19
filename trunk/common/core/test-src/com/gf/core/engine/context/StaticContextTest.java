package com.gf.core.engine.context;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.AbstractEngineTest;
import com.gf.core.engine.context.model.FirstStaticServiceExpectedFilter;
import com.gf.core.engine.context.model.SecondStaticServiceExpectedFilter;
import com.gf.core.engine.model.StaticServiceImpl;
import com.gf.core.engine.model.StaticServiceImpl2;
import com.gf.test.action.EmptyAction;
import com.gf.test.handler.EmptyHandler;

public class StaticContextTest extends AbstractEngineTest {
	
	@Test
	public void test_for_duplicates(){
		fail("todo");
	}
	
	@Test
	public void test_same_objects_order(){
		
		Engine engine = new Engine();
		engine.addToContext(new StaticServiceImpl2());
		engine.addToContext(new StaticServiceImpl());
		engine.putFilter(FirstStaticServiceExpectedFilter.class);
		engine.putHandler(EmptyHandler.class);
		engine.invoke(new EmptyAction());
		
		Engine engine2 = new Engine();
		engine2.addToContext(new StaticServiceImpl());
		engine2.addToContext(new StaticServiceImpl2());
		engine2.putFilter(SecondStaticServiceExpectedFilter.class);
		engine2.putHandler(EmptyHandler.class);
		engine2.invoke(new EmptyAction());
		
	}
	

}
