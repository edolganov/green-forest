package com.green.forest.core.engine;

import junit.framework.Assert;

import com.green.forest.api.Action;
import com.green.forest.api.extra.invocation.TraceTree;
import com.green.forest.api.key.core.action.TraceHandlers;
import com.green.forest.core.Engine;

public class EngineTest extends Assert {
	
	public static void enableTracing(Engine engine){
		engine.addValue(TraceHandlers.class, true);
	}
	
	@SuppressWarnings("unchecked")
	public static void checkTrace(Action<?,?> action, Object... traceTree){
		TraceTree expected = new TraceTree(traceTree);
		TraceTree actual = TraceHandlers.getOrCreateTrace(action);
		assertEquals(expected, actual);
	}

}
