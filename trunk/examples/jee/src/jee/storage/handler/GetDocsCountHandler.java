package jee.storage.handler;

import javax.persistence.EntityManager;

import jee.entity.DocEntity;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.action.GetDocsCount;

@Mapping(GetDocsCount.class)
public class GetDocsCountHandler extends Handler<GetDocsCount>{
	
	@Inject
	EntityManager em;

	@Override
	public void invoke(GetDocsCount action) throws Exception {
		
		Long count = (Long)em.createNamedQuery(DocEntity.Q_COUNT).getSingleResult();
		action.setOutput(count.intValue());
		
	}

}
