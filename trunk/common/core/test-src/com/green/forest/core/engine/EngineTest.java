package com.green.forest.core.engine;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import com.green.forest.api.Action;
import com.green.forest.api.extra.invocation.TraceElem;
import com.green.forest.api.extra.invocation.TraceTree;
import com.green.forest.api.key.core.action.TraceHandlers;
import com.green.forest.core.Engine;

public class EngineTest extends Assert {
	
	public static void enableTracing(Engine engine){
		engine.addValue(TraceHandlers.class, true);
	}
	
	@SuppressWarnings("unchecked")
	public static void checkTrace(Action<?,?> action, Object... traceTree){
		TraceTree trace = (TraceTree)action.getAttr(TraceHandlers.ATTR_KEY);
		List<TraceElem> level = trace.getLevel();
		
		fail("todo check trace tree");
		
		LinkedList<TraceElem> queue = new LinkedList<TraceElem>();
		queue.addAll(level);
		
		for (int i = 0; i < traceTree.length; i++) {
			Object ob = traceTree[i];
			if(ob instanceof Class){
				
				queue.removeFirst();
			}
			//assertEquals(curClass, trace.get(i));
		}
	}

}
