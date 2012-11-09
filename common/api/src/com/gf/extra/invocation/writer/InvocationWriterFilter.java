package com.gf.extra.invocation.writer;

import com.gf.extra.invocation.reader.InvocationReaderFilter;

//TODO
public abstract class InvocationWriterFilter extends InvocationReaderFilter {
	
	protected InvocationWritter invocationWritter;

	public void setInvocationWritter(InvocationWritter invocationWritter) {
		this.invocationWritter = invocationWritter;
	}
	
	

}
