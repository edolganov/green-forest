package com.gf.core.engine.context.model;

import java.util.Collection;

import junit.framework.Assert;

import com.gf.Action;
import com.gf.Filter;
import com.gf.core.context.ContextRepository;
import com.gf.core.engine.model.InvocationServiceImpl;
import com.gf.service.FilterChain;
import com.gf.util.ReflectionsUtil;

public class InvocationContextDuplicatesChecker extends Filter {

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		InvocationServiceImpl service = new InvocationServiceImpl();
		
		addToInvocationContext(service);
		addToInvocationContext(service);
		
		Object context = ReflectionsUtil.getField(this, "invocationContext");
		ContextRepository contextRepo = ReflectionsUtil.getField(context, "invocationContext");
		Collection<Object> all = contextRepo.getAll();
		Assert.assertEquals(2, all.size());
		
	}

}
