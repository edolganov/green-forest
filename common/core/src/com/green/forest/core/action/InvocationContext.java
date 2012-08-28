package com.green.forest.core.action;

import java.io.Serializable;
import java.util.List;

import com.green.forest.api.Action;
import com.green.forest.api.ConfigService;
import com.green.forest.api.Filter;
import com.green.forest.api.Handler;
import com.green.forest.api.Interceptor;
import com.green.forest.api.InvocationControll;
import com.green.forest.api.MappingObject;
import com.green.forest.api.extra.invocation.TraceTree;
import com.green.forest.api.key.core.action.TraceHandlers;

@SuppressWarnings("rawtypes")
public class InvocationContext implements InvocationControll {
	
	InvocationBlock owner;
	InvocationContext parent;
	
	public ActionServiceImpl actions; 
	public ConfigService config;
	public Action<?,?> action;
	
	boolean isTraceHandlers = false;
	
	public List<Filter> filters;
	
	public List<Interceptor> interceptors;
	public Handler handler;
	
	
	public void init(MappingObject obj){
		addToTrace(obj);
		obj.setInvocation(this);
	}
	
	public void addToTrace(Object ob) {
		if(isTraceHandlers){
			TraceTree trace = TraceHandlers.getOrCreateTrace(action);
			trace.addTraceElem(ob);
		}
	}

	@Override
	public <I extends Serializable, O extends Serializable> O subInvoke(
			Action<I, O> subAction) throws Exception {
		
		if(isTraceHandlers){
			TraceTree trace = TraceHandlers.getOrCreateTrace(action);
			TraceTree subTrace = TraceHandlers.getOrCreateTrace(subAction);
			trace.addSubTrace(subTrace);
		}
		
		return (O)owner.subInvoke(this, subAction);
	}
	


}
