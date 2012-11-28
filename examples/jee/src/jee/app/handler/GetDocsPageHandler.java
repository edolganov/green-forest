package jee.app.handler;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jee.entity.DocEntity;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.action.GetDocsPage;
import example.common.action.GetDocsPage.Input;

@Mapping(GetDocsPage.class)
public class GetDocsPageHandler extends Handler<GetDocsPage>{
	
	@Inject
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public void invoke(GetDocsPage action) throws Exception {
		
		Input input = action.input();
		int pageIndex = input.pageIndex;
		int limit = input.limit;
		
		if(pageIndex < 0){
			pageIndex = 0;
		}
		
		if(limit < 0){
			limit = 0;
		} else if(limit > 50){
			limit = 50;
		}
		
		Query q = em.createNamedQuery(DocEntity.Q_GET_PAGE);
		q.setFirstResult(limit*pageIndex);
		q.setMaxResults(limit);
		List<DocEntity> result = (List<DocEntity>)q.getResultList();
		
		
	}

}
