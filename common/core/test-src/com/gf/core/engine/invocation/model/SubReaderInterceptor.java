package com.gf.core.engine.invocation.model;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.List;

import com.gf.Handler;
import com.gf.annotation.Mapping;
import com.gf.extra.invocation.reader.InvocationReaderInterceptor;
import com.gf.service.InterceptorChain;
import com.gf.test.action.StringAction;

@Mapping(StringAction.class)
public class SubReaderInterceptor extends InvocationReaderInterceptor<StringAction>{

	@Override
	public void invoke(StringAction action, InterceptorChain chain)
			throws Exception {
		
		List<Object> beforePrev = invocationReader.getLocalPrevHandlers();
		List<Object> beforeNext = invocationReader.getLocalNextHandlers();
		
		assertEquals(0, beforePrev.size());
		assertEquals(1, beforeNext.size());
		assertTrue(Handler.class.isAssignableFrom(beforeNext.get(0).getClass()));
		
		chain.doNext();
		
		List<Object> afterPrev = invocationReader.getLocalPrevHandlers();
		List<Object> afterNext = invocationReader.getLocalNextHandlers();
		
		assertEquals(0, afterPrev.size());
		assertEquals(1, afterNext.size());
		assertTrue(Handler.class.isAssignableFrom(afterNext.get(0).getClass()));
		
	}

}
