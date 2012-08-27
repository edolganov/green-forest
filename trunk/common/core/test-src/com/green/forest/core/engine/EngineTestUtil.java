package com.green.forest.core.engine;

import java.util.List;

import com.green.forest.api.Action;
import com.green.forest.api.key.core.action.TraceHandlers;
import com.green.forest.core.Engine;

import junit.framework.Assert;

public class EngineTestUtil extends Assert {
	
	public static void prepareTrace(Engine engine){
		engine.addValue(TraceHandlers.class, true);
	}
	
	@SuppressWarnings("unchecked")
	public static void checkTrace(Action<?,?> action, Class<?>... classes){
		List<Class<?>> trace = (List<Class<?>>)action.getAttr(TraceHandlers.ATTR_KEY);
		
		assertEquals(classes.length, trace.size());
		
		for (int i = 0; i < classes.length; i++) {
			Class<?> curClass = classes[i];
			assertEquals(curClass, trace.get(i));
		}
	}

}
