package com.gf.core.engine.invocation.model;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.List;

import com.gf.Action;
import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.InvocationObject;
import com.gf.annotation.Order;
import com.gf.extra.invocation.reader.InvocationReaderFilter;
import com.gf.service.FilterChain;

@Order(2)
public class SecondReaderFilter extends InvocationReaderFilter {

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		List<InvocationObject> beforePrev = invocationReader.getLocalPrevObjects();
		List<InvocationObject> beforeNext = invocationReader.getLocalNextObjects();
		
		assertEquals(1, beforePrev.size());
		assertTrue(Filter.class.isAssignableFrom(beforePrev.get(0).getClass()));
		assertEquals(3, beforeNext.size());
		assertTrue(Interceptor.class.isAssignableFrom(beforeNext.get(0).getClass()));
		assertTrue(Interceptor.class.isAssignableFrom(beforeNext.get(1).getClass()));
		assertTrue(Handler.class.isAssignableFrom(beforeNext.get(2).getClass()));
		
		chain.doNext();
		
		List<InvocationObject> afterPrev = invocationReader.getLocalPrevObjects();
		List<InvocationObject> afterNext = invocationReader.getLocalNextObjects();
		
		assertEquals(1, afterPrev.size());
		assertTrue(Filter.class.isAssignableFrom(afterPrev.get(0).getClass()));
		assertEquals(3, afterNext.size());
		assertTrue(Interceptor.class.isAssignableFrom(afterNext.get(0).getClass()));
		assertTrue(Interceptor.class.isAssignableFrom(afterNext.get(1).getClass()));
		assertTrue(Handler.class.isAssignableFrom(afterNext.get(2).getClass()));
	}

}