package com.gf.core.engine.interceptor;

import java.util.List;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.AbstractEngineTest;
import com.gf.core.engine.filter.model.BeginFilter;
import com.gf.core.engine.handler.model.RecursionAction;
import com.gf.core.engine.handler.model.RecursionHandler;
import com.gf.core.engine.handler.model.SubInvokeAction;
import com.gf.core.engine.handler.model.SubInvokeHandler;
import com.gf.core.engine.handler.model.SubSubInvokeAction;
import com.gf.core.engine.handler.model.SubSubInvokeHandler;
import com.gf.core.engine.interceptor.model.BeginForAllByAnn;
import com.gf.core.engine.interceptor.model.FirstByAnn;
import com.gf.core.engine.interceptor.model.RecursionInterceptor;
import com.gf.core.engine.interceptor.model.SubInvokeInterceptor;
import com.gf.core.engine.interceptor.model.SubSubInvokeInterceptor;
import com.gf.exception.invoke.InvokeDepthMaxSizeException;
import com.gf.extra.trace.TraceElement;
import com.gf.extra.trace.TraceLevel;
import com.gf.extra.trace.TraceLevelItem;
import com.gf.key.core.InvokeDepthMaxSize;
import com.gf.key.core.TraceHandlers;
import com.gf.test.action.StringAction;
import com.gf.test.handler.StringEcho;
import com.gf.test.interceptor.StringReverse;
import com.gf.util.Util;

@SuppressWarnings("unchecked")
public class InvokeTest extends AbstractEngineTest {
	
	
	@Test(expected=InvokeDepthMaxSizeException.class)
	public void test_recursion_if_trace_disable(){
		Engine engine = new Engine();
		disableTracing(engine);
		
		engine.putInterceptor(RecursionInterceptor.class);
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
		
		engine.putInterceptor(RecursionInterceptor.class);
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
		
		TraceLevel level = TraceHandlers.getOrCreateTrace(action);
		while(level != null){
			depth++;
			List<TraceLevelItem> items = level.getChildrenItems();
			List<TraceElement> subTraces = items.get(0).getChildren();
			if( subTraces.size() == 2){
				level = (TraceLevel)subTraces.get(1);
			} else {
				level = null;
			}
		}
		
		assertEquals(depthMaxSize, depth);
		
	}
	
	@Test
	public void test_sub_sub_invoke(){
		
		Engine engine = new Engine();
		enableTracing(engine);
		
		engine.putInterceptor(SubSubInvokeInterceptor.class);
		engine.putInterceptor(SubInvokeInterceptor.class);
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
			SubSubInvokeInterceptor.class,
				Util.list(
					BeginForAllByAnn.class,
					SubInvokeInterceptor.class,
						Util.list(
								BeginForAllByAnn.class,
								FirstByAnn.class,
								StringEcho.class
						)
				),
				Util.list(
					BeginForAllByAnn.class,
					SubInvokeInterceptor.class,
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
		
		engine.putInterceptor(SubInvokeInterceptor.class);
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
			SubInvokeInterceptor.class,
				Util.list(
						BeginForAllByAnn.class,
						FirstByAnn.class,
						StringEcho.class)
			);
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
