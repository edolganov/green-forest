package com.gf.core.engine.invocation.model;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.List;

import com.gf.Action;
import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.annotation.Order;
import com.gf.extra.invocation.reader.InvocationReaderFilter;
import com.gf.service.FilterChain;

@Order(1)
public class FirstReaderFilter extends InvocationReaderFilter {

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		List<Object> beforePrev = invocationReader.getLocalPrevHandlers();
		List<Object> beforeNext = invocationReader.getLocalNextHandlers();
		
		assertEquals(0, beforePrev.size());
		assertEquals(4, beforeNext.size());
		assertTrue(Filter.class.isAssignableFrom(beforeNext.get(0).getClass()));
		assertTrue(Interceptor.class.isAssignableFrom(beforeNext.get(1).getClass()));
		assertTrue(Interceptor.class.isAssignableFrom(beforeNext.get(2).getClass()));
		assertTrue(Handler.class.isAssignableFrom(beforeNext.get(3).getClass()));
		
		chain.doNext();
		
		List<Object> afterPrev = invocationReader.getLocalPrevHandlers();
		List<Object> afterNext = invocationReader.getLocalNextHandlers();
		
		assertEquals(0, afterPrev.size());
		assertEquals(4, afterNext.size());
		assertTrue(Filter.class.isAssignableFrom(afterNext.get(0).getClass()));
		assertTrue(Interceptor.class.isAssignableFrom(afterNext.get(1).getClass()));
		assertTrue(Interceptor.class.isAssignableFrom(afterNext.get(2).getClass()));
		assertTrue(Handler.class.isAssignableFrom(afterNext.get(3).getClass()));
		
	}

}
