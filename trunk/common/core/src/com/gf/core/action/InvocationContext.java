package com.gf.core.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.gf.Action;
import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.MappingObject;
import com.gf.annotation.Inject;
import com.gf.core.action.trace.TraceWrapper;
import com.gf.core.context.ContextRepository;
import com.gf.core.util.ReflectionsUtil;
import com.gf.extra.invocation.TraceList;
import com.gf.key.core.TraceHandlers;
import com.gf.service.ConfigService;
import com.gf.service.InvocationContextService;
import com.gf.service.InvocationService;

@SuppressWarnings("rawtypes")
public class InvocationContext implements InvocationService, InvocationContextService {
	
	InvocationBlock owner;
	InvocationContext parent;
	int depth;
	ContextRepository invocationContext;
	
	
	public ActionServiceImpl actions; 
	public ConfigService config;
	public Action<?,?> action;
	public List<Filter> filters;
	public List<Interceptor> interceptors;
	public Handler handler;
	public Collection<Object> staticContextObjects;
	public TraceWrapper traceWrapper;
	
	
	public void initMappingObject(MappingObject obj){
		
		injectAllContexts(obj);
		obj.setInvocation(this);
	}

	public void initFilter(Filter obj){
		
		injectAllContexts(obj);
		obj.setInvocationContext(this);
		
	}
	
	
	private void injectAllContexts(Object obj) {
		
		Collection<Object> invocationContextObjects = invocationContext.getAll();
		
		ArrayList<Object> list = new ArrayList<Object>();
		list.addAll(invocationContextObjects);
		list.addAll(staticContextObjects);

		inject(obj, list);
		
	}
	
	
	private void addToTrace_(Object ob) {
			TraceList trace = TraceHandlers.getOrCreateTrace(action);
			trace.createAndAddItem(ob);
	}

	
	private void inject(Object obj, Collection<Object> collection){
		ReflectionsUtil.injectDataFromContext(obj, collection, Inject.class);
	}
	

	@Override
	public <I, O> O subInvoke(Action<I, O> subAction) throws Exception {
		
			TraceList trace = TraceHandlers.getOrCreateTrace(action);
			TraceList subTrace = TraceHandlers.getOrCreateTrace(subAction);
			trace.addSubListToLastItem(subTrace);
		
		return (O)owner.subInvoke(this, subAction);
	}

	@Override
	public void addToInvocationContext(Object ob) {
		invocationContext.add(ob);
	}
	


}
