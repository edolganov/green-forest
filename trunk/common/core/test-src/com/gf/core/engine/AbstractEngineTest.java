package com.gf.core.engine;

import java.util.List;

import junit.framework.Assert;

import com.gf.Action;
import com.gf.core.Engine;
import com.gf.extra.invocation.TraceItem;
import com.gf.extra.invocation.TraceTree;
import com.gf.key.core.TraceHandlers;

public abstract class AbstractEngineTest extends Assert {
	
	public static void enableTracing(Engine engine){
		engine.setConfig(TraceHandlers.class, true);
	}
	
	public static void disableTracing(Engine engine){
		engine.setConfig(TraceHandlers.class, false);
	}
	
	public static void checkTrace(Action<?,?> action, Object... traceTree){
		TraceTree expected = new TraceTree(traceTree);
		TraceTree actual = TraceHandlers.getOrCreateTrace(action);
		checkTrace(expected, actual);
	}

	public static void checkTrace(TraceTree expected, TraceTree actual) {
		List<TraceItem> itemsA = expected.getItems();
		List<TraceItem> itemsB = actual.getItems();
		assertEquals("traces size", itemsA.size(), itemsB.size());
		for (int i = 0; i < itemsA.size(); i++) {
			TraceItem itemA = itemsA.get(i);
			TraceItem itemB = itemsB.get(i);
			checkTreeItem(itemA, itemB);
		}
	}

	public static void checkTreeItem(TraceItem expected, TraceItem actual) {
		assertEquals("items types", expected.type, actual.type);
		List<TraceTree> subTracesA = expected.getSubTraces();
		List<TraceTree> subTracesB = actual.getSubTraces();
		assertEquals("subtraces size", subTracesA.size(), subTracesB.size());
		for (int i = 0; i < subTracesA.size(); i++) {
			TraceTree itemA = subTracesA.get(i);
			TraceTree itemB = subTracesB.get(i);
			checkTrace(itemA, itemB);
		}
	}

}
