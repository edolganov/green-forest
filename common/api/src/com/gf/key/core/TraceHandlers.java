package com.gf.key.core;

import com.gf.Action;
import com.gf.config.ConfigKey;
import com.gf.extra.invocation.TraceTree;

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
	
	public static TraceTree getOrCreateTrace(Action<?, ?> action){
		TraceTree trace = getTrace(action);
		if(trace == null){
			trace = new TraceTree();
			setTrace(action, trace);
		}
		return trace;
	}
	
	public static TraceTree getTrace(Action<?, ?> action){
		return (TraceTree) action.getAttr(ATTR_KEY);
	}
	
	private static void setTrace(Action<?, ?> action, TraceTree trace) {
		action.putAttr(ATTR_KEY, trace);
	}

}
