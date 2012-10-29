package com.gf.key.core;

import com.gf.Action;
import com.gf.config.ConfigKey;
import com.gf.extra.invocation.TraceList;

public class TraceHandlers extends ConfigKey<Boolean> {
	
	public static final String ATTR_KEY = TraceHandlers.class.getName()+".TraceTree";

	@Override
	public boolean hasDefaultValue() {
		return true;
	}
	
	@Override
	public Boolean getDefaultValue() throws Exception {
		return Boolean.FALSE;
	}
	
	public static TraceList getOrCreateTrace(Action<?, ?> action){
		TraceList trace = getTrace(action);
		if(trace == null){
			trace = new TraceList();
			setTrace(action, trace);
		}
		return trace;
	}
	
	public static TraceList getTrace(Action<?, ?> action){
		return (TraceList) action.getAttr(ATTR_KEY);
	}
	
	private static void setTrace(Action<?, ?> action, TraceList trace) {
		action.putAttr(ATTR_KEY, trace);
	}

}
