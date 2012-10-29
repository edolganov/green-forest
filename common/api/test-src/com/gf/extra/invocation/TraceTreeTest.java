package com.gf.extra.invocation;

import java.util.List;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.gf.extra.invocation.TraceItem;
import com.gf.extra.invocation.TraceList;
import com.gf.util.Util;

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
		
		System.out.println(new TraceList());
		System.out.println();
		
		TraceList trace = new TraceList(
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
		
		assertTrue(new TraceList().equals(new TraceList()));
		assertTrue(new TraceList().hashCode() == new TraceList().hashCode());
		
		TraceList trace1 = new TraceList(
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
		
		
		TraceList trace2 = new TraceList(
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
		
		
		TraceList trace3 = new TraceList();
		assertFalse(trace1.equals(trace3));
		assertFalse(trace1.hashCode() == trace3.hashCode());
		
		
		TraceList trace4 = new TraceList(
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
		
		assertFalse(trace1.equals(new TraceList(
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
		
		assertFalse(trace1.equals(new TraceList(
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
		
		assertFalse(trace1.equals(new TraceList(
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
		
		assertFalse(trace1.equals(new TraceList(
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
		
		TraceList trace = new TraceList(
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
		assertEquals(0, items.get(0).getSubLists().size());
		//1
		assertEquals(Byte.class, items.get(1).type);
		{
			List<TraceList> subTraces = items.get(1).getSubLists();
			assertEquals(2, subTraces.size());
			//0
			{
				List<TraceItem> subItems = subTraces.get(0).getItems();
				assertEquals(1, subItems.size());
				assertEquals(Object.class, subItems.get(0).type);
				assertEquals(0, subItems.get(0).getSubLists().size());
			}
			//1
			{
				List<TraceItem> subItems = subTraces.get(1).getItems();
				assertEquals(1, subItems.size());
				assertEquals(Long.class, subItems.get(0).type);
				{
					List<TraceList> subSubTraces = subItems.get(0).getSubLists();
					assertEquals(1, subSubTraces.size());
					//0
					{
						List<TraceItem> subSubItems = subSubTraces.get(0).getItems();
						assertEquals(1, subSubItems.size());
						assertEquals(Character.class, subSubItems.get(0).type);
						assertEquals(0, subSubItems.get(0).getSubLists().size());
					}
				}

			}
		}
		//2
		assertEquals(Integer.class, items.get(2).type);
		assertEquals(0, items.get(2).getSubLists().size());
	}

}
