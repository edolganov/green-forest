package com.gf.core.engine.tracing;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.AbstractEngineTest;
import com.gf.extra.invocation.TraceLevel;
import com.gf.key.core.TraceHandlers;
import com.gf.test.action.StringAction;
import com.gf.test.handler.StringEcho;

public class TracingTest extends AbstractEngineTest {
	
	
	@Test
	public void test_trace_sub_engine_with_disable_root_tracing(){
		fail("todo");
	}
	
	@Test
	public void test_trace_sub_engine_with_different_action(){
		fail("todo");
	}
	
	@Test
	public void test_trace_sub_engine_with_same_action(){
		fail("todo");
	}
	
	@Test
	public void test_enable_tracing(){
		
		Engine engine = new Engine();
		engine.putHandler(StringEcho.class);
		engine.setConfig(TraceHandlers.class, true);
		
		StringAction action = new StringAction();
		engine.invoke(action);
		
		TraceLevel trace = TraceHandlers.getTrace(action);
		assertNotNull(trace);
	}
	
	
	@Test
	public void test_default_not_tracing(){
		
		Engine engine = new Engine();
		engine.putHandler(StringEcho.class);
		
		StringAction action = new StringAction();
		engine.invoke(action);
		
		TraceLevel trace = TraceHandlers.getTrace(action);
		assertNull(trace);

	}

}
