package com.gf.core.engine.tx.invocation.model;

import static junit.framework.Assert.assertEquals;

import java.util.List;

import com.gf.Action;
import com.gf.annotation.Order;
import com.gf.extra.invocation.reader.InvocationReaderFilter;
import com.gf.service.FilterChain;

@Order(1)
public class FirstReaderFilter extends InvocationReaderFilter {

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		List<Object> beforePrev = invocationReader.getPrevHandlers();
		List<Object> beforeNext = invocationReader.getNextHandlers();
		
		assertEquals(0, beforePrev.size());
		assertEquals(2, beforeNext.size());
		assertEquals(SecondReaderFilter.class, beforeNext.get(0).getClass());
		
		chain.doNext();
		
		List<Object> afterPrev = invocationReader.getPrevHandlers();
		List<Object> afterNext = invocationReader.getNextHandlers();
		
	}

}
