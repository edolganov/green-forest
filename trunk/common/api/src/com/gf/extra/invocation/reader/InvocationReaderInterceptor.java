package com.gf.extra.invocation.reader;

import com.gf.Action;
import com.gf.Interceptor;

public abstract class InvocationReaderInterceptor<T extends Action<?,?>> 
	extends Interceptor<T> implements HasInvocationReader {
	
	protected InvocationReader invocationReader;

	@Override
	public void setInvocationReader(InvocationReader invocationReader) {
		this.invocationReader = invocationReader;
	}

}
