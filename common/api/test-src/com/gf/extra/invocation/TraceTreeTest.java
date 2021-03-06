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
package com.gf.extra.invocation;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.gf.extra.trace.TraceElement;
import com.gf.extra.trace.TraceLevel;
import com.gf.extra.trace.TraceLevelItem;
import com.gf.util.Util;
import com.gf.util.junit.AssertExt;

@SuppressWarnings("unchecked")
public class TraceTreeTest extends AssertExt {
	
	@Ignore
	@Test
	public void test_timework_of_every_trace_elem(){
		fail_TODO();
	}
	
	@Ignore
	@Test
	public void test_toString(){
		
		System.out.println(new TraceLevel());
		System.out.println();
		
		TraceLevel trace = new TraceLevel(
				String.class,
					Util.list(
						Byte.class, 
							Util.list(
								Object.class),
							Util.list(
								Long.class,
								Character.class)),
				Integer.class
				);
		
		System.out.println(trace);
		System.out.println();
		
		System.out.println(new TraceLevelItem(null));
		System.out.println();
		
		System.out.println(trace.getChildrenItems().get(0));
		System.out.println();
		
		System.out.println(trace.getChildrenItems().get(1));
		System.out.println();
	}
	
	
	@Test
	public void test_equals_and_hash(){
		
		assertTrue(new TraceLevel().equals(new TraceLevel()));
		assertTrue(new TraceLevel().hashCode() == new TraceLevel().hashCode());
		
		TraceLevel trace1 = new TraceLevel(
				String.class,
					Util.list(
						Byte.class, 
							Util.list(
								Object.class),
							Util.list(
								Long.class,
								Character.class)),
				Integer.class
				);
		
		
		TraceLevel trace2 = new TraceLevel(
				String.class,
					Util.list(
						Byte.class, 
							Util.list(
								Object.class),
							Util.list(
								Long.class,
								Character.class)),
				Integer.class
				);
		
		assertTrue(trace1.equals(trace2));
		assertTrue(trace1.hashCode() == trace2.hashCode());
		
		
		TraceLevel trace3 = new TraceLevel();
		assertFalse(trace1.equals(trace3));
		assertFalse(trace1.hashCode() == trace3.hashCode());
		
		
		TraceLevel trace4 = new TraceLevel(
				String.class,
					Util.list(
						Byte.class, 
							Util.list(
								Object.class),
							Util.list(
								Long.class)), //<--
				Integer.class
				);
		assertFalse(trace1.equals(trace4));
		assertFalse(trace1.hashCode() == trace4.hashCode());
		
		assertFalse(trace1.equals(new TraceLevel(
				String.class,
					Util.list(
						Byte.class, 
							Util.list(
								Long.class, //<--
								Character.class)),
							Util.list(
								Object.class),
				Integer.class
				)));
		
		assertFalse(trace1.equals(new TraceLevel(
				String.class,
					Util.list(
						Byte.class, 
							Util.list(
								Object.class),
							Util.list(
								Long.class,
								Character.class,
								Character.class)),//<--
				Integer.class
				)));
		
		assertFalse(trace1.equals(new TraceLevel(
				String.class,
					Util.list(
						Byte.class, 
							Util.list(
								Object.class),
							Util.list(
								Long.class,
								Character.class)),
				Integer.class,
				Integer.class //<--
				)));
		
		assertFalse(trace1.equals(new TraceLevel(
				String.class,
					Util.list(
						Byte.class, 
							Util.list(
								Object.class),
							Util.list(
								Long.class,
								Character.class))
					//<--
				)));
	}
	
	
	@Test
	public void test_build(){
		
		TraceLevel trace = new TraceLevel(
				String.class,
				Byte.class,
					Util.list(
						Object.class),
					Util.list(
						Long.class,
							Util.list(
								Character.class)),
				Integer.class
				);
		
		List<TraceLevelItem> items = trace.getChildrenItems();
		assertEquals(3, items.size());
		
		//0
		assertEquals(String.class, items.get(0).owner);
		assertEquals(0, items.get(0).getChildren().size());
		//1
		assertEquals(Byte.class, items.get(1).owner);
		{
			List<TraceElement> subTraces = items.get(1).getChildren();
			assertEquals(2, subTraces.size());
			//0
			{
				List<TraceElement> subItems = subTraces.get(0).getChildren();
				assertEquals(1, subItems.size());
				assertEquals(Object.class, ((TraceLevelItem)subItems.get(0)).owner);
				assertEquals(0, subItems.get(0).getChildren().size());
			}
			//1
			{
				List<TraceElement> subItems = subTraces.get(1).getChildren();
				assertEquals(1, subItems.size());
				assertEquals(Long.class, ((TraceLevelItem)subItems.get(0)).owner);
				{
					List<TraceElement> subSubTraces = subItems.get(0).getChildren();
					assertEquals(1, subSubTraces.size());
					//0
					{
						List<TraceElement> subSubItems = subSubTraces.get(0).getChildren();
						assertEquals(1, subSubItems.size());
						assertEquals(Character.class, ((TraceLevelItem)subSubItems.get(0)).owner);
						assertEquals(0, subSubItems.get(0).getChildren().size());
					}
				}

			}
		}
		//2
		assertEquals(Integer.class, items.get(2).owner);
		assertEquals(0, items.get(2).getChildren().size());
	}

}
