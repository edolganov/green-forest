package com.green.forest.core.engine;

import java.util.List;

import junit.framework.Assert;

import com.green.forest.api.Action;
import com.green.forest.api.extra.invocation.TraceItem;
import com.green.forest.api.extra.invocation.TraceTree;
import com.green.forest.api.key.core.action.TraceHandlers;
import com.green.forest.core.Engine;

public class EngineTest extends Assert {
	
	public static void enableTracing(Engine engine){
		engine.addValue(TraceHandlers.class, true);
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
