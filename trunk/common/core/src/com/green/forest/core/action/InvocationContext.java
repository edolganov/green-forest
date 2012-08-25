package com.green.forest.core.action;

import java.util.List;

import com.green.forest.api.Action;
import com.green.forest.api.Filter;
import com.green.forest.api.Handler;
import com.green.forest.api.Interceptor;

@SuppressWarnings("rawtypes")
public class InvocationContext {
	
	public ActionServiceImpl actionService; 
	public Action<?,?> action;
	public List<Filter> filters;
	public List<Interceptor> interceptors;
	public Handler handler;

}
