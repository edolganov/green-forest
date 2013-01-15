/*
 * Copyright 2012 Evgeny Dolganov (evgenij.dolganov@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gf.core.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.gf.Action;
import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.InvocationObject;
import com.gf.MappingObject;
import com.gf.annotation.Inject;
import com.gf.core.action.reader.InvocationReaderImpl;
import com.gf.core.action.trace.TraceWrapper;
import com.gf.core.context.ContextRepository;
import com.gf.extra.invocation.InvocationObjectInitializer;
import com.gf.extra.invocation.reader.HasInvocationReader;
import com.gf.extra.invocation.reader.InvocationReader;
import com.gf.extra.util.ReflectionsUtil;
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
	public List<Filter> filters = Collections.emptyList();
	public List<Interceptor> interceptors = Collections.emptyList();
	public Handler handler;
	public Collection<Object> staticContextObjects;
	public TraceWrapper traceWrapper;
	public List<InvocationObjectInitializer> initializers;
	
	
	public void initMappingObject(MappingObject ob) throws Exception{
		init(ob);
		ob.setInvocation(this);
	}

	public void initFilter(Filter ob) throws Exception{
		init(ob);
		ob.setInvocationContext(this);
	}

	private void init(InvocationObject obj) throws Exception {
		
		Collection<Object> invocationContextObjects = invocationContext.getAll();
		ArrayList<Object> list = new ArrayList<Object>();
		list.addAll(invocationContextObjects);
		list.addAll(staticContextObjects);
		
		for(InvocationObjectInitializer initializer : initializers){
			initializer.beforeObjectInited(obj, list);
		}
		
		//context
		inject(obj, list);
		
		//config
		obj.setConfigService(config);
		
		//extra
		setInvocationReader(obj);
		
		
		for(InvocationObjectInitializer initializer : initializers){
			initializer.afterObjectInited(obj, list);
		}
		
	}
	
	private void setInvocationReader(InvocationObject ob) {
		if(ob instanceof HasInvocationReader){
			InvocationReader reader = new InvocationReaderImpl(this, ob);
			((HasInvocationReader)ob).setInvocationReader(reader);
		}
	}

	
	private void inject(Object obj, Collection<Object> collection){
		ReflectionsUtil.injectDataFromContext(obj, collection, Inject.class);
	}
	

	public <I, O> O subInvoke(Action<I, O> subAction) throws Exception {		
		return (O)owner.subInvoke(this, subAction);
	}

	public void addToInvocationContext(Object ob) {
		invocationContext.add(ob);
	}
	


}
