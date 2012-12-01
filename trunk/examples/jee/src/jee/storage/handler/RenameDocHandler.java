package jee.storage.handler;

import javax.persistence.EntityManager;

import jee.entity.DocEntity;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.action.RenameDoc;
import example.common.model.Doc;

@Mapping(RenameDoc.class)
public class RenameDocHandler extends Handler<RenameDoc>{
	
	@Inject
	EntityManager em;

	@Override
	public void invoke(RenameDoc action) throws Exception {
		
		Doc input = action.input();
		
		//update
		DocEntity entity = new DocEntity(input);
		em.merge(entity);
		
	}

}
