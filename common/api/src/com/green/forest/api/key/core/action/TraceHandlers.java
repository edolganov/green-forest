package com.green.forest.api.key.core.action;

import com.green.forest.api.Action;
import com.green.forest.api.config.ConfigKey;
import com.green.forest.api.extra.invocation.TraceTree;

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
