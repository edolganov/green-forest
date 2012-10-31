package com.gf.key.core;

import com.gf.Action;
import com.gf.config.ConfigKey;
import com.gf.extra.invocation.TraceLevel;

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
	
	public static TraceLevel getOrCreateTrace(Action<?, ?> action){
		TraceLevel trace = getTrace(action);
		if(trace == null){
			trace = new TraceLevel();
			setTrace(action, trace);
		}
		return trace;
	}
	
	public static TraceLevel getTrace(Action<?, ?> action){
		return (TraceLevel) action.getAttr(ATTR_KEY);
	}
	
	private static void setTrace(Action<?, ?> action, TraceLevel trace) {
		action.putAttr(ATTR_KEY, trace);
	}

}
