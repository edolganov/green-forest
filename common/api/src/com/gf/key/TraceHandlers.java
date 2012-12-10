package com.gf.key;

import com.gf.Action;
import com.gf.config.ConfigKey;
import com.gf.extra.trace.Trace;

public class TraceHandlers extends ConfigKey<Boolean> {
	
	public static final String ATTR_KEY = TraceHandlers.class.getName()+".Trace";

	@Override
	public boolean hasDefaultValue() {
		return true;
	}
	
	@Override
	public Boolean getDefaultValue() throws Exception {
		return Boolean.FALSE;
	}
	
	public static Trace getOrCreateTrace(Action<?, ?> action){
		Trace trace = getTrace(action);
		if(trace == null){
			trace = new Trace();
			setTrace(action, trace);
		}
		return trace;
	}
	
	public static Trace getTrace(Action<?, ?> action){
		return (Trace) action.getAttr(ATTR_KEY);
	}
	
	public static void setTrace(Action<?, ?> action, Trace trace) {
		action.putAttr(ATTR_KEY, trace);
	}

}
