package com.gf.core.engine.handler;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.AbstractEngineTest;
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
import com.gf.extra.invocation.TraceLevelItem;
import com.gf.extra.invocation.TraceLevel;
import com.gf.key.core.InvokeDepthMaxSize;
import com.gf.key.core.TraceHandlers;
import com.gf.test.action.StringAction;
import com.gf.test.handler.WithoutMappingHandler;
import com.gf.test.handler.StringEcho;
import com.gf.util.Util;
import com.gf.util.test.concurrent.ThreadRacer;
import com.gf.util.test.concurrent.ThreadsRace;

@SuppressWarnings("unchecked")
public class InvokeTest extends AbstractEngineTest {
	
	
	@Ignore
	@Test(expected=HandlerNotFoundException.class)
	public void test_no_handler_concurrent(){
		
		final Engine engine = new Engine();
		
		ThreadsRace race = new ThreadsRace();
		
		race.startFromBegin(new ThreadRacer(){

			@Override
			public void invoke() throws Exception {
				
				engine.putHandler(StringEcho.class);
				fireAndWait("handler added", "handler removed");
				engine.invoke(new StringAction("test"));
			}
			
		});
		
		race.startWithEvent("handler added", new ThreadRacer() {
			
			@Override
			public void invoke() throws Exception {
				
				//engine.removeHandler(StringEcho.class);
				fire("handler removed");
			}
		});
		
		race.invokeWithRuntimeExceptions();
		
	}
	
	
	@Test(expected=InvokeDepthMaxSizeException.class)
	public void test_recursion_if_trace_disable(){
		Engine engine = new Engine();
		disableTracing(engine);
		
		engine.putHandler(RecursionHandler.class);
		engine.putHandler(SubInvokeHandler.class);
		engine.putHandler(StringEcho.class);
		
		engine.invoke(new RecursionAction());
	}
	
	
	@Test
	public void test_recursion(){
		
		Engine engine = new Engine();
		enableTracing(engine);
		
		int depthMaxSize = 42;
		engine.setConfig(InvokeDepthMaxSize.class, depthMaxSize);
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
		
		TraceLevel trace = TraceHandlers.getOrCreateTrace(action);
		while(trace != null){
			depth++;
			List<TraceLevelItem> items = trace.getItems();
			List<TraceLevel> subTraces = items.get(0).getSubLists();
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
		engine.putHandler(WithoutMappingHandler.class);
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
