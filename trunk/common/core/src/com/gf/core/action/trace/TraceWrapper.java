package com.gf.core.action.trace;

import com.gf.extra.invocation.InvocaitonEndStatus;
import com.gf.extra.invocation.Trace;
import com.gf.extra.invocation.TraceLevelItem;
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
		} finally {
			finallyInvocaitonTrace();
		}
	}

	private void startInvocaitonTrace() {
		if( ! data.isTracing) {
			return;
		}
		
		curTrace = new Trace();
		curTrace.start();
		if(isRoot){
			data.rootTrace = curTrace;
		} else {
			data.rootTrace.getLevel().addSubListToLastItem(curTrace);
		}
	}
		
	
	private void successStopInvocaitonTrace() {
		if( ! data.isTracing) {
			return;
		}
		curTrace.setEndStatus(InvocaitonEndStatus.SUCCESSED);
	}
	
	private void failStopInvocaitonTrace(Throwable t) {
		if( ! data.isTracing) {
			return;
		}
		curTrace.setEndStatus(InvocaitonEndStatus.WITH_EXCEPTION);
		curTrace.setThrowable(t);
	}
	
	private void finallyInvocaitonTrace() {
		if( ! data.isTracing) {
			return;
		}
		curTrace.stop();
		if(isRoot){
			THREAD_LOCAL_DATA.remove();
		}
	}


	
	
	
	public void wrapHandler(Object handler, Body body) throws Exception {
		
		TraceLevelItem item = startHandlerTrace(handler);
		try {
			body.invocation();
			successStopHandlerTrace(item);
		}catch (Throwable t) {
			failStopHandlerTrace(item, t);
			throw ExceptionUtil.getExceptionOrThrowError(t);
		}finally {
			finallyHandlerTrace(item);
		}
	}

	private TraceLevelItem startHandlerTrace(Object handler) {
		if( ! data.isTracing) {
			return null;
		}
		
		TraceLevelItem item = curTrace.getLevel().createAndAddItem(handler);
		item.start();
		return item;
	}

	private void successStopHandlerTrace(TraceLevelItem item) {
		if( ! data.isTracing) {
			return;
		}
		item.setEndStatus(InvocaitonEndStatus.SUCCESSED);
	}
	
	private void failStopHandlerTrace(TraceLevelItem item, Throwable t) {
		if( ! data.isTracing) {
			return;
		}
		item.setEndStatus(InvocaitonEndStatus.WITH_EXCEPTION);
		item.setThrowable(t);
	}
	
	private void finallyHandlerTrace(TraceLevelItem item) {
		item.stop();
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
		public Trace rootTrace;

		public Data(boolean isTracing) {
			super();
			this.isTracing = isTracing;
		}
		
		
	}

}

