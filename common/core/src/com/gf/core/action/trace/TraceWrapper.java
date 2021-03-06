/*
 * Copyright 2012 Evgeny Dolganov (evgenij.dolganov@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gf.core.action.trace;

import java.util.LinkedList;
import java.util.List;

import com.gf.Action;
import com.gf.extra.trace.InvocaitonEndStatus;
import com.gf.extra.trace.Trace;
import com.gf.extra.trace.TraceElement;
import com.gf.extra.trace.TraceLevel;
import com.gf.extra.trace.TraceLevelItem;
import com.gf.key.TraceHandlers;
import com.gf.util.ExceptionUtil;
import com.gf.util.Util;

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
			if(isTracing){
				THREAD_LOCAL_DATA.set(d);
			}
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
			addSubLevelToItem(parent, trace);
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
		addSubLevelToItem(parent, level);
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

	private void addSubLevelToItem(TraceElement parent, TraceLevel level) {
		
		Util.checkState(parent instanceof TraceLevel, "expected type: "+TraceLevel.class);
		
		List<TraceElement> children = parent.getChildren();
		if(children.size()==0){
			throw new IllegalStateException("can't invoke wrapSubHandlers without wrapInvocationBlock");
		}
		TraceElement lastChild = children.get(children.size()-1);
		lastChild.addChild(level);
		
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
		public final LinkedList<TraceElement> parentsQueue;

		public Data(boolean isTracing) {

			this.isTracing = isTracing;
			
			if(isTracing){
				parentsQueue = new LinkedList<TraceElement>();
			} else {
				parentsQueue = null;
			}
		}
		
		
	}

}

