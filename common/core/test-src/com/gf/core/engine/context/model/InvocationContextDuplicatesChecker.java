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
