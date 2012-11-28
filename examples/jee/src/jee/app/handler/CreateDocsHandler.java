package jee.app.handler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import jee.entity.DocEntity;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;
import com.gf.util.Util;

import example.common.action.CheckDocName;
import example.common.action.CreateDocs;
import example.common.model.Doc;

@Mapping(CreateDocs.class)
public class CreateDocsHandler extends Handler<CreateDocs>{
	
	@Inject
	EntityManager em;

	@Override
	public void invoke(CreateDocs action) throws Exception {
		
		List<String> names = action.input();
		if(Util.isEmpty(names)){
			return;
		}
		
		//validation
		for (String name : names) {
			subInvoke(new CheckDocName(name));
		}
		
		ArrayList<Doc> out = new ArrayList<Doc>();
		
		//creation
		for (String name : names) {
			
			DocEntity entity = new DocEntity(null, name);
			em.persist(entity);
			
			Doc doc = new Doc(entity.getId(), name);
			out.add(doc);
		}
		
		action.setOutput(out);
		
	}

}
