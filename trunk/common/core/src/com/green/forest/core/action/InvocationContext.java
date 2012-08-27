package com.green.forest.core.action;

import java.util.List;

import com.green.forest.api.Action;
import com.green.forest.api.ConfigService;
import com.green.forest.api.Filter;
import com.green.forest.api.Handler;
import com.green.forest.api.Interceptor;
import com.green.forest.api.key.core.action.TraceHandlers;

@SuppressWarnings("rawtypes")
public class InvocationContext {
	
	//services
	public ActionServiceImpl actions; 
	public ConfigService config;
	
	//flags
	boolean isTraceHandlers = false;
	
	//invocation objects
	public Action<?,?> action;
	public List<Filter> filters;
	public List<Interceptor> interceptors;
	public Handler handler;
	
	
	public void addToTrace(Object ob) {
		if(isTraceHandlers){
			List<Class<?>> list = TraceHandlers.getOrCreateTraceList(action);
			list.add(ob.getClass());
		}
	}

}
