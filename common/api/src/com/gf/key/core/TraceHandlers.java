package com.gf.key.core;

import com.gf.Action;
import com.gf.config.ConfigKey;
import com.gf.extra.invocation.TraceTree;

public class TraceHandlers extends ConfigKey<Boolean> {
	
	public static final String ATTR_KEY = "gf.core.action.TraceTree";

	@Override
	public boolean hasDefaultValue() {
		return true;
	}
	
	@Override
	public Boolean getDefaultValue() throws Exception {
		return Boolean.FALSE;
	}
	
	public static TraceTree getOrCreateTrace(Action<?, ?> action){
		TraceTree trace = (TraceTree) action.getAttr(ATTR_KEY);
		if(trace == null){
			trace = new TraceTree();
			action.putAttr(ATTR_KEY, trace);
		}
		return trace;
	}

}
