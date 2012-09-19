package com.gf.core.context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import com.gf.ConfigService;
import com.gf.ContextService;

public class ContextServiceImpl implements ContextService, StaticContext {
	
	private CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<Object>();
	
	ConfigService config;

	public ContextServiceImpl(ConfigService config) {
		this.config = config;
	}

	@Override
	public void addToContext(Object ob) {
		list.add(ob);
	}

	@Override
	public Collection<Object> getStaticContextObjects() {
		ArrayList<Object> out = new ArrayList<Object>(list);
		return out;
	}

}
