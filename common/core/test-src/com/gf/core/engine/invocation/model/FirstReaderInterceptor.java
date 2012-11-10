package com.gf.core.engine.invocation.model;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.List;

import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.InvocationObject;
import com.gf.annotation.Mapping;
import com.gf.annotation.Order;
import com.gf.extra.invocation.reader.InvocationReaderInterceptor;
import com.gf.service.InterceptorChain;
import com.gf.test.action.EmptyAction;

@Order(1)
@Mapping(EmptyAction.class)
public class FirstReaderInterceptor extends InvocationReaderInterceptor<EmptyAction>{

	@Override
	public void invoke(EmptyAction action, InterceptorChain chain)
			throws Exception {

		List<InvocationObject> beforePrev = invocationReader.getLocalPrevObjects();
		List<InvocationObject> beforeNext = invocationReader.getLocalNextObjects();
		
		assertEquals(2, beforePrev.size());
		assertTrue(Filter.class.isAssignableFrom(beforePrev.get(0).getClass()));
		assertTrue(Filter.class.isAssignableFrom(beforePrev.get(1).getClass()));
		assertEquals(2, beforeNext.size());
		assertTrue(Interceptor.class.isAssignableFrom(beforeNext.get(0).getClass()));
		assertTrue(Handler.class.isAssignableFrom(beforeNext.get(1).getClass()));
		
		chain.doNext();
		
		List<InvocationObject> afterPrev = invocationReader.getLocalPrevObjects();
		List<InvocationObject> afterNext = invocationReader.getLocalNextObjects();
		
		assertEquals(2, afterPrev.size());
		assertTrue(Filter.class.isAssignableFrom(afterPrev.get(0).getClass()));
		assertTrue(Filter.class.isAssignableFrom(afterPrev.get(1).getClass()));
		assertEquals(2, afterNext.size());
		assertTrue(Interceptor.class.isAssignableFrom(afterNext.get(0).getClass()));
		assertTrue(Handler.class.isAssignableFrom(afterNext.get(1).getClass()));
		
	}

}
