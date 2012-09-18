package com.gf.core.action;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.gf.Action;
import com.gf.ConfigService;
import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.InvocationControll;
import com.gf.MappingObject;
import com.gf.annotation.Inject;
import com.gf.core.util.ReflectionUtil;
import com.gf.exception.invoke.InjectException;
import com.gf.extra.invocation.TraceTree;
import com.gf.key.core.TraceHandlers;

@SuppressWarnings("rawtypes")
public class InvocationContext implements InvocationControll {
	
	InvocationBlock owner;
	InvocationContext parent;
	boolean isTraceHandlers = false;
	int depth;
	
	
	public ActionServiceImpl actions; 
	public ConfigService config;
	public Action<?,?> action;
	public List<Filter> filters;
	public List<Interceptor> interceptors;
	public Handler handler;
	public Collection<Object> staticContextObjects;
	
	
	public void initMappingObject(MappingObject obj){
		
		injectStaticAndRuntime(obj);
		addToTrace(obj);
		obj.setInvocation(this);
	}

	public void initFilter(Filter obj){
		
		injectStatic(obj);
		addToTrace(obj);
		
	}
	
	private void addToTrace(Object ob) {
		if(isTraceHandlers){
			TraceTree trace = TraceHandlers.getOrCreateTrace(action);
			trace.createAndAddItem(ob);
		}
	}
	
	private void injectStaticAndRuntime(Object obj) {
		injectStatic(obj);
	}
	
	private void injectStatic(Object obj) {
		inject(obj, staticContextObjects);
	}
	

	
	private void inject(Object obj, Collection<Object> collection){
		try {
			ReflectionUtil.injectDataFromContext(obj, collection, Inject.class);
		}catch (Exception e) {
			throw new InjectException(obj, e);
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
