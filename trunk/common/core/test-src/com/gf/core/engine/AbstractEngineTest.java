package com.gf.core.engine;

import java.util.List;

import junit.framework.Assert;

import com.gf.Action;
import com.gf.core.Engine;
import com.gf.extra.invocation.TraceItem;
import com.gf.extra.invocation.TraceList;
import com.gf.key.core.TraceHandlers;

public abstract class AbstractEngineTest extends Assert {
	
	public static void enableTracing(Engine engine){
		engine.setConfig(TraceHandlers.class, true);
	}
	
	public static void disableTracing(Engine engine){
		engine.setConfig(TraceHandlers.class, false);
	}
	
	public static void checkTrace(Action<?,?> action, Object... traceTree){
		TraceList expected = new TraceList(traceTree);
		TraceList actual = TraceHandlers.getOrCreateTrace(action);
		checkTrace(expected, actual);
	}

	public static void checkTrace(TraceList expected, TraceList actual) {
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
		List<TraceList> subTracesA = expected.getSubLists();
		List<TraceList> subTracesB = actual.getSubLists();
		assertEquals("subtraces size", subTracesA.size(), subTracesB.size());
		for (int i = 0; i < subTracesA.size(); i++) {
			TraceList itemA = subTracesA.get(i);
			TraceList itemB = subTracesB.get(i);
			checkTrace(itemA, itemB);
		}
	}

}
