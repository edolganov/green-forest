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
package jee.storage.handler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import jee.entity.DocEntity;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.action.CreateDocs;
import example.common.model.Doc;

@Mapping(CreateDocs.class)
public class CreateDocsHandler extends Handler<CreateDocs>{
	
	@Inject
	EntityManager em;

	@Override
	public void invoke(CreateDocs action) throws Exception {
		
		List<String> names = action.input();
		List<Doc> out = new ArrayList<Doc>();
		
		for (String name : names) {
			
			DocEntity entity = new DocEntity(null, name);
			em.persist(entity);
			
			Doc doc = entity.getDoc();
			out.add(doc);
		}
		
		action.setOutput(out);
		
	}

}
