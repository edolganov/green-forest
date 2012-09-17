package com.gf.core.action;

import java.io.Serializable;
import java.util.List;

import com.gf.Action;
import com.gf.ConfigService;
import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.InvocationControll;
import com.gf.MappingObject;
import com.gf.extra.invocation.TraceTree;
import com.gf.key.core.TraceHandlers;

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
			trace.createAndAddItem(ob);
		}
	}

	@Override
	public <I extends Serializable, O extends Serializable> O subInvoke(
			Action<I, O> subAction) throws Exception {
		
		if(isTraceHandlers){
			TraceTree trace = TraceHandlers.getOrCreateTrace(action);
			TraceTree subTrace = TraceHandlers.getOrCreateTrace(subAction);
			trace.addSubTraceToLastItem(subTrace);
		}
		
		return (O)owner.subInvoke(this, subAction);
	}
	


}
