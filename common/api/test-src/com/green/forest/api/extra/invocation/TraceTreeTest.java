package com.green.forest.api.extra.invocation;

import java.util.List;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.green.forest.util.Util;

@SuppressWarnings("unchecked")
public class TraceTreeTest extends Assert {
	
	@Ignore
	@Test
	public void test_timework_of_every_trace_elem(){
		fail("todo");
	}
	
	@Ignore
	@Test
	public void test_toString(){
		
		System.out.println(new TraceTree());
		System.out.println();
		
		TraceTree trace = new TraceTree(
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
		
		System.out.println(new TraceItem(null));
		System.out.println();
		
		System.out.println(trace.getItems().get(0));
		System.out.println();
		
		System.out.println(trace.getItems().get(1));
		System.out.println();
	}
	
	
	@Test
	public void test_equals_and_hash(){
		
		assertTrue(new TraceTree().equals(new TraceTree()));
		assertTrue(new TraceTree().hashCode() == new TraceTree().hashCode());
		
		TraceTree trace1 = new TraceTree(
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
		
		
		TraceTree trace2 = new TraceTree(
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
		
		
		TraceTree trace3 = new TraceTree();
		assertFalse(trace1.equals(trace3));
		assertFalse(trace1.hashCode() == trace3.hashCode());
		
		
		TraceTree trace4 = new TraceTree(
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
		
		assertFalse(trace1.equals(new TraceTree(
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
		
		assertFalse(trace1.equals(new TraceTree(
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
		
		assertFalse(trace1.equals(new TraceTree(
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
		
		assertFalse(trace1.equals(new TraceTree(
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
		
		TraceTree trace = new TraceTree(
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
		
		List<TraceItem> items = trace.getItems();
		assertEquals(3, items.size());
		
		//0
		assertEquals(String.class, items.get(0).type);
		assertEquals(0, items.get(0).getSubTraces().size());
		//1
		assertEquals(Byte.class, items.get(1).type);
		{
			List<TraceTree> subTraces = items.get(1).getSubTraces();
			assertEquals(2, subTraces.size());
			//0
			{
				List<TraceItem> subItems = subTraces.get(0).getItems();
				assertEquals(1, subItems.size());
				assertEquals(Object.class, subItems.get(0).type);
				assertEquals(0, subItems.get(0).getSubTraces().size());
			}
			//1
			{
				List<TraceItem> subItems = subTraces.get(1).getItems();
				assertEquals(1, subItems.size());
				assertEquals(Long.class, subItems.get(0).type);
				{
					List<TraceTree> subSubTraces = subItems.get(0).getSubTraces();
					assertEquals(1, subSubTraces.size());
					//0
					{
						List<TraceItem> subSubItems = subSubTraces.get(0).getItems();
						assertEquals(1, subSubItems.size());
						assertEquals(Character.class, subSubItems.get(0).type);
						assertEquals(0, subSubItems.get(0).getSubTraces().size());
					}
				}

			}
		}
		//2
		assertEquals(Integer.class, items.get(2).type);
		assertEquals(0, items.get(2).getSubTraces().size());
	}

}
