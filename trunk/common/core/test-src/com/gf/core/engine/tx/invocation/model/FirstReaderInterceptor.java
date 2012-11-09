package com.gf.core.engine.tx.invocation.model;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.List;

import com.gf.Action;
import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.annotation.Mapping;
import com.gf.annotation.Order;
import com.gf.extra.invocation.reader.InvocationReaderInterceptor;
import com.gf.service.InterceptorChain;

@Order(1)
@Mapping(Action.class)
public class FirstReaderInterceptor extends InvocationReaderInterceptor<Action<?,?>>{

	@Override
	public void invoke(Action<?, ?> action, InterceptorChain chain)
			throws Exception {

		List<Object> beforePrev = invocationReader.getPrevHandlers();
		List<Object> beforeNext = invocationReader.getNextHandlers();
		
		assertEquals(2, beforePrev.size());
		assertTrue(Filter.class.isAssignableFrom(beforePrev.get(0).getClass()));
		assertTrue(Filter.class.isAssignableFrom(beforePrev.get(1).getClass()));
		assertEquals(2, beforeNext.size());
		assertTrue(Interceptor.class.isAssignableFrom(beforeNext.get(0).getClass()));
		assertTrue(Handler.class.isAssignableFrom(beforeNext.get(1).getClass()));
		
		chain.doNext();
		
		List<Object> afterPrev = invocationReader.getPrevHandlers();
		List<Object> afterNext = invocationReader.getNextHandlers();
		
		assertEquals(2, afterPrev.size());
		assertTrue(Filter.class.isAssignableFrom(afterPrev.get(0).getClass()));
		assertTrue(Filter.class.isAssignableFrom(afterPrev.get(1).getClass()));
		assertEquals(2, afterNext.size());
		assertTrue(Interceptor.class.isAssignableFrom(afterNext.get(0).getClass()));
		assertTrue(Handler.class.isAssignableFrom(afterNext.get(1).getClass()));
		
	}

}
