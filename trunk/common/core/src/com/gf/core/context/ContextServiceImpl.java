package com.gf.core.context;

import java.util.Collection;
import com.gf.service.ContextService;

public class ContextServiceImpl implements ContextService, StaticContext {
	
	private ContextRepository repo = new ContextRepository();

	@Override
	public void addToContext(Object ob) {
		repo.add(ob);
	}

	@Override
	public Collection<Object> getStaticContextObjects() {
		return repo.getAll();
	}

}
