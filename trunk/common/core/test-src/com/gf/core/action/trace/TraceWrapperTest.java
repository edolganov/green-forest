package com.gf.core.action.trace;

import junit.framework.Assert;

import org.junit.Test;

import com.gf.key.core.TraceHandlers;
import com.gf.test.action.StringAction;

public class TraceWrapperTest extends Assert {
	
	Body emptyBody = new Body() {
		
		@Override
		public void invocation() throws Throwable {}
		
	};
	
	@Test
	public void test_thread_local_clean() throws Exception{
		
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
