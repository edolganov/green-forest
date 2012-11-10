package com.gf.extra.invocation.reader;

import com.gf.Filter;

public abstract class InvocationReaderFilter extends Filter implements HasInvocationReader {
	
	protected InvocationReader invocationReader;

	@Override
	public void setInvocationReader(InvocationReader invocationReader) {
		this.invocationReader = invocationReader;
	}
	

}
