package com.gf.core.action.trace;

import java.util.LinkedList;
import java.util.List;

import com.gf.Action;
import com.gf.extra.invocation.InvocaitonEndStatus;
import com.gf.extra.invocation.Trace;
import com.gf.extra.invocation.TraceElement;
import com.gf.extra.invocation.TraceLevel;
import com.gf.extra.invocation.TraceLevelItem;
import com.gf.key.core.TraceHandlers;
import com.gf.util.ExceptionUtil;

@SuppressWarnings("rawtypes")
public class TraceWrapper {
	
	private static final ThreadLocal<Data> THREAD_LOCAL_DATA = new ThreadLocal<Data>();
	
	static boolean isEmptyThreadLocal(){
		return THREAD_LOCAL_DATA.get() == null;
	}
	
	private Data d;
	private final boolean isRoot;
	
	public TraceWrapper(boolean isTracing){
		
		d = THREAD_LOCAL_DATA.get();
		if(d == null){
			isRoot = true;
			d = new Data(isTracing);
			THREAD_LOCAL_DATA.set(d);
		} else {
			isRoot = false;
		}
		
	}
	
	public void wrapInvocationBlock(Object owner, Action action, Body body) throws Exception {
		
		if( ! d.isTracing) {
			simpleInvocation(body);
			return;
		}
		
		Trace trace = new Trace(owner);
		trace.start();
		
		if( ! isRoot){
			TraceElement parent = d.parentsQueue.getLast();
			parent.addChild(trace);
		}
		d.parentsQueue.addLast(trace);
		
		try {
			body.invocation();
			trace.setEndStatus(InvocaitonEndStatus.SUCCESSED);
		}catch (Throwable t) {
			trace.setEndStatus(InvocaitonEndStatus.WITH_EXCEPTION);
			trace.setThrowable(t);
			throw ExceptionUtil.getExceptionOrThrowError(t);
		} finally {
			
			trace.stop();
			TraceHandlers.setTrace(action, trace);
			
			d.parentsQueue.removeLast();
			if(isRoot){
				THREAD_LOCAL_DATA.remove();
			}
		}
	}


	public void wrapHandler(Object handler, Body body) throws Exception {
		
		if( ! d.isTracing) {
			simpleInvocation(body);
			return;
		}
		
		TraceLevelItem item = new TraceLevelItem(handler);
		item.start();
		
		TraceElement parent = d.parentsQueue.getLast();
		parent.addChild(item);

		try {
			body.invocation();
			item.setEndStatus(InvocaitonEndStatus.SUCCESSED);
		}catch (Throwable t) {
			item.setEndStatus(InvocaitonEndStatus.WITH_EXCEPTION);
			item.setThrowable(t);
			throw ExceptionUtil.getExceptionOrThrowError(t);
		}finally {
			item.stop();
		}
	}
	
	public void wrapSubHandlers(Body body) throws Exception {
		
		if( ! d.isTracing) {
			simpleInvocation(body);
			return;
		}
		
		TraceLevel level = new TraceLevel();
		level.start();
		
		TraceElement parent = d.parentsQueue.getLast();
		List<TraceElement> children = parent.getChildren();
		if(children.size()==0){
			throw new IllegalStateException("can't invoke wrapSubHandlers without wrapInvocationBlock");
		}
		TraceElement lastChild = children.get(children.size()-1);
		lastChild.addChild(level);
		d.parentsQueue.addLast(level);
		
		try {
			body.invocation();
			level.setEndStatus(InvocaitonEndStatus.SUCCESSED);
		}catch (Throwable t) {
			level.setEndStatus(InvocaitonEndStatus.WITH_EXCEPTION);
			level.setThrowable(t);
			throw ExceptionUtil.getExceptionOrThrowError(t);
		}finally{
			level.stop();
			d.parentsQueue.removeLast();
		}
	}
	

	private void simpleInvocation(Body body)throws Exception {
		try {
			body.invocation();
		}catch (Throwable t) {
			throw ExceptionUtil.getExceptionOrThrowError(t);
		}
	}




	private static class Data {
		
		public final boolean isTracing;
		public final LinkedList<TraceElement> parentsQueue = new LinkedList<TraceElement>();

		public Data(boolean isTracing) {
			super();
			this.isTracing = isTracing;
		}
		
		
	}

}

