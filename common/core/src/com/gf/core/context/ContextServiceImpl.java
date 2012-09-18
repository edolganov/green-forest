package com.gf.core.context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArraySet;

import com.gf.ConfigService;
import com.gf.ContextService;

public class ContextServiceImpl implements ContextService, StaticContext {
	
	private CopyOnWriteArraySet<Object> set = new CopyOnWriteArraySet<Object>();
	
	ConfigService config;

	public ContextServiceImpl(ConfigService config) {
		this.config = config;
	}

	@Override
	public void addToContext(Object ob) {
		set.add(ob);
	}

	@Override
	public Collection<Object> getStaticContextObjects() {
		ArrayList<Object> out = new ArrayList<Object>(set);
		return out;
	}

}
