package com.gf.core.action;

import java.util.List;

import com.gf.Action;
import com.gf.core.action.filter.FiltersBlock;
import com.gf.core.action.interceptor.InterceptorsBlock;
import com.gf.core.context.ContextRepository;
import com.gf.exception.invoke.InvokeDepthMaxSizeException;
import com.gf.key.core.InvokeDepthMaxSize;
import com.gf.key.core.TraceHandlers;


public class InvocationBlock {
	
	private boolean isTraceHandlers;
	private int depthMaxSize;
	
	ActionServiceImpl actionService;
	Action<?,?> action;
	
	
	public InvocationBlock(ActionServiceImpl actionService, Action<?,?> action){
		this.actionService = actionService;
		this.action = action;
		
		isTraceHandlers = actionService.config.isTrue(new TraceHandlers());
		depthMaxSize = actionService.config.getValue(new InvokeDepthMaxSize());
	}
	
	public void invoke() throws Exception {
		
		InvocationContext c = createContext(action, null, true);
		
		FiltersBlock block = new FiltersBlock(c);
		block.invoke();
		
	}
	
	
	<I, O> O subInvoke(InvocationContext parent, Action<I, O> action) throws Exception {
		
		InvocationContext c = createContext(action, parent, false);
		
		InterceptorsBlock block = new InterceptorsBlock(c);
		block.invoke();
		
		return (O) action.getOutput();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private InvocationContext createContext(Action<?,?> action, InvocationContext parent, boolean initFilters){
		
		//check depth
		int depth = parent == null? 1 : parent.depth+1;
		if(depth > depthMaxSize){
			throw new InvokeDepthMaxSizeException(depthMaxSize);
		}
		
		//create new context
		InvocationContext c = new InvocationContext();
		c.owner = this;
		c.parent = parent;
		c.depth = depth;
		c.actions = actionService;
		c.action = action;
		
		//prepare services
		c.config = c.actions.config;
		
		//prepare flags
		c.isTraceHandlers = isTraceHandlers;
		
		//prepare context
		c.staticContextObjects = c.actions.staticContext.getStaticContextObjects();
		c.invocationContext = parent == null? new ContextRepository() : parent.invocationContext;
		
		//prepare handlers
		if(initFilters){
			c.filters = c.actions.resourse.getFilters();
		}
		c.interceptors = (List)c.actions.resourse.getInterceptors(c.action);
		c.handler = c.actions.resourse.getHandler(c.action);
		
		return c;
	}

}
