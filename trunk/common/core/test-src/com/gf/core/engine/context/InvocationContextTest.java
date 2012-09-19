package com.gf.core.engine.context;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.AbstractEngineTest;
import com.gf.core.engine.context.model.FirstInvocationContextSetterFilter;
import com.gf.core.engine.context.model.FirstInvocationServiceExpectedFilter;
import com.gf.core.engine.context.model.SecondInvocationContextSetterFilter;
import com.gf.core.engine.context.model.SecondInvocationServiceExpectedFilter;
import com.gf.test.action.EmptyAction;
import com.gf.test.handler.EmptyHandler;

public class InvocationContextTest extends AbstractEngineTest {
	
	@Test
	public void test_for_duplicates(){
		fail("todo");
	}
	
	@Test
	public void test_same_objects_order(){
		
		Engine engine = new Engine();
		engine.putFilter(FirstInvocationContextSetterFilter.class);
		engine.putFilter(FirstInvocationServiceExpectedFilter.class);
		engine.putHandler(EmptyHandler.class);
		engine.invoke(new EmptyAction());
		
		Engine engine2 = new Engine();
		engine2.putFilter(SecondInvocationContextSetterFilter.class);
		engine2.putFilter(SecondInvocationServiceExpectedFilter.class);
		engine2.putHandler(EmptyHandler.class);
		engine2.invoke(new EmptyAction());
	}
	

}
