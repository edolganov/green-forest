package com.gf.extra.invocation.writer;

import com.gf.Action;
import com.gf.extra.invocation.reader.InvocationReaderInterceptor;

//TODO
public abstract class InvocationWriterInterceptor<T extends Action<?,?>> 
	extends InvocationReaderInterceptor<T>{
	
	protected InvocationWritter invocationWritter;

	public void setInvocationWritter(InvocationWritter invocationWritter) {
		this.invocationWritter = invocationWritter;
	}

}
