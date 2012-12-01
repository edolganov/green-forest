package jee.storage.handler;

import javax.persistence.EntityManager;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.action.CreateDataBase;
import example.common.action.GetDocsCount;

@Mapping(CreateDataBase.class)
public class CreateDataBaseHandler extends Handler<CreateDataBase>{
	
	@Inject
	EntityManager em;

	@Override
	public void invoke(CreateDataBase action) throws Exception {
		
		Integer count = subInvoke(new GetDocsCount());
		boolean emptyDB = count == 0;
		action.setOutput(emptyDB);
		
	}
	

}
