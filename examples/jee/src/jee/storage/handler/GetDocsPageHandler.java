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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jee.entity.DocEntity;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.action.GetDocsCount;
import example.common.action.GetDocsPage;
import example.common.action.GetDocsPage.Input;
import example.common.model.Doc;
import example.common.model.Page;

@Mapping(GetDocsPage.class)
public class GetDocsPageHandler extends Handler<GetDocsPage>{
	
	@Inject
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public void invoke(GetDocsPage action) throws Exception {
		
		Input input = action.input();
		
		int limit = input.limit;
		int pageIndex = input.pageIndex;
		int offset = pageIndex*limit;
		
		Query q = em.createNamedQuery(DocEntity.Q_GET_PAGE);
		q.setFirstResult(offset);
		q.setMaxResults(limit);
		List<DocEntity> result = (List<DocEntity>)q.getResultList();
		
		Integer count = subInvoke(new GetDocsCount());
		
		List<Doc> list = DocEntity.getDocs(result);
		Page<Doc> page = new Page<Doc>(list, pageIndex, limit, count);
		action.setOutput(page);
		
	}

}
