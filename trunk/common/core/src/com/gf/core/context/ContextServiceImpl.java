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

	@Override
	public void setContextObjects(Collection<Object> objects) {
		if(objects == null) return;
		for (Object ob : objects) {
			addToContext(ob);
		}
	}

}
