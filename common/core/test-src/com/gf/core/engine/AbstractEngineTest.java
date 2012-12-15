/*
 * Copyright 2012 Evgeny Dolganov (evgenij.dolganov@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gf.core.engine;

import java.util.List;

import com.gf.Action;
import com.gf.core.Engine;
import com.gf.extra.trace.TraceElement;
import com.gf.extra.trace.TraceLevel;
import com.gf.extra.trace.TraceLevelItem;
import com.gf.key.TraceHandlers;
import com.gf.util.junit.AssertExt;

public abstract class AbstractEngineTest extends AssertExt {
	
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
		List<TraceLevelItem> itemsA = expected.getChildrenItems();
		List<TraceLevelItem> itemsB = actual.getChildrenItems();
		assertEquals("traces size", itemsA.size(), itemsB.size());
		for (int i = 0; i < itemsA.size(); i++) {
			TraceLevelItem itemA = itemsA.get(i);
			TraceLevelItem itemB = itemsB.get(i);
			checkTreeItem(itemA, itemB);
		}
	}

	public static void checkTreeItem(TraceLevelItem expected, TraceLevelItem actual) {
		assertEquals("items owners", expected.owner, actual.owner.getClass());
		List<TraceElement> subTracesA = expected.getChildren();
		List<TraceElement> subTracesB = actual.getChildren();
		assertEquals("subtraces size", subTracesA.size(), subTracesB.size());
		for (int i = 0; i < subTracesA.size(); i++) {
			
			TraceElement elemA = subTracesA.get(i);
			TraceElement elemB = subTracesB.get(i);
			assertTrue(elemA.getClass().isAssignableFrom(elemB.getClass()));
			
			if(elemA instanceof TraceLevel){
				checkTrace((TraceLevel)elemA, (TraceLevel)elemB);
			}

		}
	}

}
