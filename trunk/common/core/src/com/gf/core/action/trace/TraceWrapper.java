package com.gf.core.action.trace;

import com.gf.extra.invocation.Trace;
import com.gf.util.ExceptionUtil;

public class TraceWrapper {
	
	private static final ThreadLocal<Data> THREAD_LOCAL_DATA = new ThreadLocal<Data>();
	
	private boolean isRoot;
	private Data data;
	private Object owner;
	private Trace curTrace;
	
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
	
	private void startInvocaitonTrace() {
		if( ! data.isTracing) {
			return;
		}
		
		curTrace = new Trace();
		if(isRoot){
			data.trace = curTrace;
		} else {
			data.trace.getLevel().addSubListToLastItem(curTrace);
		}
	}
		
	
	private void successStopInvocaitonTrace() {
		if( ! data.isTracing) {
			return;
		}
		
		if(isRoot){
			THREAD_LOCAL_DATA.remove();
		}
	}

	private void failStopInvocaitonTrace(Throwable t) {
		if( ! data.isTracing) {
			return;
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
	
	public void wrapSubHandlers(Body body) throws Exception {
		try {
			startSubHandlerTrace();
			body.invocation();
			successStopSubHandlerTrace();
		}catch (Throwable t) {
			failStopSubHandlerTrace(t);
			throw ExceptionUtil.getExceptionOrThrowError(t);
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
	
	
	private void startSubHandlerTrace() {
		if( ! data.isTracing) {
			return;
		}
		
	}

	private void successStopSubHandlerTrace() {
		if( ! data.isTracing) {
			return;
		}
	}

	private void failStopSubHandlerTrace(Throwable t) {
		if( ! data.isTracing) {
			return;
		}
	}





	private static class Data {
		
		public boolean isTracing;
		public Trace trace;

		public Data(boolean isTracing) {
			super();
			this.isTracing = isTracing;
		}
		
		
	}

}

