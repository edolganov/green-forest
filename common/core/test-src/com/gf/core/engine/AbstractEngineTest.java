package com.gf.core.engine;

import java.util.List;

import junit.framework.Assert;

import com.gf.Action;
import com.gf.core.Engine;
import com.gf.extra.invocation.TraceLevelItem;
import com.gf.extra.invocation.TraceLevel;
import com.gf.key.core.TraceHandlers;

public abstract class AbstractEngineTest extends Assert {
	
	public static void enableTracing(Engine engine){
		engine.setConfig(TraceHandlers.class, true);
	}
	
	public static void disableTracing(Engine engine){
		engine.setConfig(TraceHandlers.class, false);
	}
	
	public static void checkTrace(Action<?,?> action, Object... traceTree){
		TraceLevel expected = new TraceLevel(traceTree);
		TraceLevel actual = TraceHandlers.getOrCreateTrace(action);
		checkTrace(expected, actual);
	}

	public static void checkTrace(TraceLevel expected, TraceLevel actual) {
		List<TraceLevelItem> itemsA = expected.getItems();
		List<TraceLevelItem> itemsB = actual.getItems();
		assertEquals("traces size", itemsA.size(), itemsB.size());
		for (int i = 0; i < itemsA.size(); i++) {
			TraceLevelItem itemA = itemsA.get(i);
			TraceLevelItem itemB = itemsB.get(i);
			checkTreeItem(itemA, itemB);
		}
	}

	public static void checkTreeItem(TraceLevelItem expected, TraceLevelItem actual) {
		assertEquals("items types", expected.type, actual.type);
		List<TraceLevel> subTracesA = expected.getSubLists();
		List<TraceLevel> subTracesB = actual.getSubLists();
		assertEquals("subtraces size", subTracesA.size(), subTracesB.size());
		for (int i = 0; i < subTracesA.size(); i++) {
			TraceLevel itemA = subTracesA.get(i);
			TraceLevel itemB = subTracesB.get(i);
			checkTrace(itemA, itemB);
		}
	}

}
