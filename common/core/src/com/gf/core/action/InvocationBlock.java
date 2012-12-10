package com.gf.core.action;

import java.util.List;

import com.gf.Action;
import com.gf.core.action.filter.FiltersBlock;
import com.gf.core.action.interceptor.InterceptorsBlock;
import com.gf.core.action.trace.Body;
import com.gf.core.action.trace.TraceWrapper;
import com.gf.core.context.ContextRepository;
import com.gf.exception.invoke.InvokeDepthMaxSizeException;
import com.gf.key.InvokeDepthMaxSize;
import com.gf.key.TraceHandlers;


public class InvocationBlock {
	
	private boolean isTraceHandlers;
	private int depthMaxSize;
	
	ActionServiceImpl actionService;
	Action<?,?> action;
	
	
	public InvocationBlock(ActionServiceImpl actionService, Action<?,?> action){
		this.actionService = actionService;
		this.action = action;
		
		isTraceHandlers = actionService.config.isTrueConfig(new TraceHandlers());
		depthMaxSize = actionService.config.getConfig(new InvokeDepthMaxSize());
	}
	
	public void invoke() throws Exception {
		
		final InvocationContext c = createContext(action, null, true);
		
		c.traceWrapper.wrapInvocationBlock(actionService.owner, action, new Body() {
			
			@Override
			public void invocation() throws Throwable {
				FiltersBlock block = new FiltersBlock(c);
				block.invoke();
			}
		});

		
	}
	
	
	<I, O> O subInvoke(InvocationContext parent, Action<I, O> action) throws Exception {
		
		final InvocationContext c = createContext(action, parent, false);
		
		c.traceWrapper.wrapSubHandlers(new Body() {
			
			@Override
			public void invocation() throws Throwable {
				InterceptorsBlock block = new InterceptorsBlock(c);
				block.invoke();
			}
		});

		
		return (O) action.getOutput();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private InvocationContext createContext(Action<?,?> action, InvocationContext parent, boolean initFilters){
		
		//check depth size
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
		c.traceWrapper = new TraceWrapper(isTraceHandlers);
		c.config = c.actions.config;
		c.staticContextObjects = c.actions.staticContext.getStaticContextObjects();
		c.invocationContext = parent == null? new ContextRepository() : parent.invocationContext;
		
		if(initFilters){
			c.filters = c.actions.resourse.getFilters();
		}
		c.interceptors = (List)c.actions.resourse.getInterceptors(c.action);
		c.handler = c.actions.resourse.getHandler(c.action);
		
		return c;
	}

}
