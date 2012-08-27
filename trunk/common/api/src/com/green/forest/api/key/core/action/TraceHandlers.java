package com.green.forest.api.key.core.action;

import java.util.ArrayList;
import java.util.List;

import com.green.forest.api.Action;
import com.green.forest.api.config.ConfigKey;

public class TraceHandlers extends ConfigKey<Boolean> {
	
	public static final String ATTR_KEY = "gf.core.action.HandlersTrace";

	@Override
	public boolean hasDefaultValue() {
		return true;
	}
	
	@Override
	public Boolean getDefaultValue() throws Exception {
		return Boolean.FALSE;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Class<?>> getOrCreateTraceList(Action<?, ?> action){
		List<Class<?>> list = (List<Class<?>>) action.getAttr(ATTR_KEY);
		if(list == null){
			list = new ArrayList<Class<?>>();
			action.putAttr(ATTR_KEY, list);
		}
		return list;
	}

}
