package com.gf.core.engine.handler;

import java.util.List;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.EngineTest;
import com.gf.core.engine.filter.model.BeginFilter;
import com.gf.core.engine.handler.model.RecursionAction;
import com.gf.core.engine.handler.model.RecursionHandler;
import com.gf.core.engine.handler.model.SubInvokeHandler;
import com.gf.core.engine.handler.model.SubInvokeAction;
import com.gf.core.engine.handler.model.SubSubInvokeHandler;
import com.gf.core.engine.handler.model.SubSubInvokeAction;
import com.gf.core.engine.interceptor.model.BeginForAllByAnn;
import com.gf.core.engine.interceptor.model.FirstByAnn;
import com.gf.exception.deploy.NoMappingAnnotationException;
import com.gf.exception.invoke.HandlerNotFoundException;
import com.gf.exception.invoke.InvokeDepthMaxSizeException;
import com.gf.extra.invocation.TraceItem;
import com.gf.extra.invocation.TraceTree;
import com.gf.key.core.InvokeDepthMaxSize;
import com.gf.key.core.TraceHandlers;
import com.gf.test.action.StringAction;
import com.gf.test.handler.HandlerWithoutMapping;
import com.gf.test.handler.StringEcho;
import com.gf.util.Util;

@SuppressWarnings("unchecked")
public class BasicTest extends EngineTest {
	
	@Test
	public void test_inject_invocation_context_from_filter(){
		fail("todo");
	}
	
	
	@Test
	public void test_inject(){
		fail("todo");
	}
	
	
	@Test
	public void test_recursion(){
		
		Engine engine = new Engine();
		enableTracing(engine);
		
		int depthMaxSize = 42;
		engine.addValue(InvokeDepthMaxSize.class, depthMaxSize);
		engine.putHandler(RecursionHandler.class);
		engine.putHandler(SubInvokeHandler.class);
		engine.putHandler(StringEcho.class);
		
		RecursionAction action = new RecursionAction();
		try {
			engine.invoke(action);
			fail("ex exp");
		}catch (InvokeDepthMaxSizeException e) {
			//ok
		}
		
		int depth = 1;
		
		TraceTree trace = TraceHandlers.getOrCreateTrace(action);
		while(trace != null){
			depth++;
			List<TraceItem> items = trace.getItems();
			List<TraceTree> subTraces = items.get(0).getSubTraces();
			if( subTraces.size() == 2){
				trace = subTraces.get(1);
			} else {
				trace = null;
			}
		}
		
		assertEquals(depthMaxSize, depth);
	}
	
	
	@Test
	public void test_sub_sub_invoke(){
		
		Engine engine = new Engine();
		enableTracing(engine);
		
		engine.putHandler(SubInvokeHandler.class);
		engine.putHandler(StringEcho.class);
		engine.putHandler(SubSubInvokeHandler.class);
		engine.putFilter(BeginFilter.class);
		engine.putInterceptor(BeginForAllByAnn.class);
		engine.putInterceptor(FirstByAnn.class);
		
		SubSubInvokeAction action = new SubSubInvokeAction();
		engine.invoke(action);
		
		checkTrace(action, 
			BeginFilter.class,
			BeginForAllByAnn.class,
			SubSubInvokeHandler.class,
				Util.list(
					BeginForAllByAnn.class,
					SubInvokeHandler.class,
						Util.list(
								BeginForAllByAnn.class,
								FirstByAnn.class,
								StringEcho.class
						)
				),
				Util.list(
					BeginForAllByAnn.class,
					SubInvokeHandler.class,
						Util.list(
								BeginForAllByAnn.class,
								FirstByAnn.class,
								StringEcho.class
						)
				)
			);
	}
	
	
	@Test
	public void test_sub_invoke(){
		
		Engine engine = new Engine();
		enableTracing(engine);
		
		engine.putHandler(SubInvokeHandler.class);
		engine.putHandler(StringEcho.class);
		engine.putFilter(BeginFilter.class);
		engine.putInterceptor(BeginForAllByAnn.class);
		engine.putInterceptor(FirstByAnn.class);
		
		SubInvokeAction action = new SubInvokeAction();
		engine.invoke(action);
		
		checkTrace(action, 
			BeginFilter.class,
			BeginForAllByAnn.class,
			SubInvokeHandler.class,
				Util.list(
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
