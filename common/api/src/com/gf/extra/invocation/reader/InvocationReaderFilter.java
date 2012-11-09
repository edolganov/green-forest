package com.gf.extra.invocation.reader;

import com.gf.Filter;

public abstract class InvocationReaderFilter extends Filter {
	
	protected InvocationReader invocationReader;

	public void setInvocationReader(InvocationReader invocationReader) {
		this.invocationReader = invocationReader;
	}
	

}
