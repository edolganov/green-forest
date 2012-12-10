package com.gf.core.action.trace;

import java.util.List;

import org.junit.Test;

import com.gf.extra.trace.Trace;
import com.gf.extra.trace.TraceElement;
import com.gf.key.TraceHandlers;
import com.gf.test.action.StringAction;
import com.gf.util.junit.AssertExt;

public class TraceWrapperTest extends AssertExt {
	
	Body emptyBody = new Body() {
		
		@Override
		public void invocation() throws Throwable {}
		
	};
	
	@Test
	public void test_sub_wrapper() throws Exception {
		
		
		final TraceWrapper wrapper = new TraceWrapper(true);
		final TraceWrapper subWrapper = new TraceWrapper(true);
		final StringAction action = new StringAction();
		
		wrapper.wrapInvocationBlock(this, action, new Body() {
			
			@Override
			public void invocation() throws Throwable {
				wrapper.wrapHandler(this, new Body() {
					
					@Override
					public void invocation() throws Throwable {
						subWrapper.wrapInvocationBlock(this, action, emptyBody);
					}
				});
			}
		});
		
		Trace trace = TraceHandlers.getTrace(action);
		List<TraceElement> children = trace.getChildren();
		assertEquals(1, children.size());
		List<TraceElement> subTrace = children.get(0).getChildren();
		assertEquals(1, subTrace.size());
		
	}
	
	
	@Test
	public void test_multi_create_clean() throws Exception{
		new TraceWrapper(false);
		assertTrue(TraceWrapper.isEmptyThreadLocal());
	}
	
	@Test
	public void test_one_level_clean() throws Exception{
		
		final TraceWrapper wrapper = new TraceWrapper(true);
		StringAction action = new StringAction();
		wrapper.wrapInvocationBlock(this, action, new Body() {
			
			@Override
			public void invocation() throws Throwable {
				wrapper.wrapHandler(new Object(), emptyBody);
				wrapper.wrapHandler(new Object(), emptyBody);
				wrapper.wrapHandler(new Object(), emptyBody);
			}
		});
		
		assertNotNull(TraceHandlers.getTrace(action));
		assertTrue(TraceWrapper.isEmptyThreadLocal());
		
	}

}
