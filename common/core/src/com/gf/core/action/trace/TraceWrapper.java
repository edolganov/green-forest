package com.gf.core.action.trace;

import com.gf.util.ExceptionUtil;

public class TraceWrapper {
	
	private static final ThreadLocal<Data> THREAD_LOCAL_DATA = new ThreadLocal<Data>();
	
	private boolean isRoot;
	private Data data;
	private Object owner;
	
	public TraceWrapper(Object owner, boolean isTracing){
		
		this.owner = owner;
		
		data = THREAD_LOCAL_DATA.get();
		if(data == null){
			isRoot = true;
			data = new Data(isTracing);
			THREAD_LOCAL_DATA.set(data);
		} else {
			isRoot = false;
		}
		
	}
	
	public void wrapInvocationBlock(Body body) throws Exception {
		try {
			startInvocaitonTrace();
			body.invocation();
			successStopInvocaitonTrace();
		}catch (Throwable t) {
			failStopInvocaitonTrace(t);
			throw ExceptionUtil.getExceptionOrThrowError(t);
		}
	}
	
	public void wrapHandler(Body body) throws Exception {
		try {
			startHandlerTrace();
			body.invocation();
			successStopHandlerTrace();
		}catch (Throwable t) {
			failStopHandlerTrace(t);
			throw ExceptionUtil.getExceptionOrThrowError(t);
		}
	}
	

	private void startInvocaitonTrace() {
		if( ! data.isTracing) {
			return;
		}
	}
		
	
	private void successStopInvocaitonTrace() {
		if( ! data.isTracing) {
			return;
		}
	}

	private void failStopInvocaitonTrace(Throwable t) {
		if( ! data.isTracing) {
			return;
		}
	}
	
	private void startHandlerTrace() {
		if( ! data.isTracing) {
			return;
		}
	}

	private void successStopHandlerTrace() {
		if( ! data.isTracing) {
			return;
		}
	}

	private void failStopHandlerTrace(Throwable t) {
		if( ! data.isTracing) {
			return;
		}
	}





	private static class Data {
		
		public boolean isTracing;

		public Data(boolean isTracing) {
			super();
			this.isTracing = isTracing;
		}
		
		
	}

}

