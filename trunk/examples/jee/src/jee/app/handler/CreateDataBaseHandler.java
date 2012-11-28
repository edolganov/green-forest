package jee.app.handler;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.action.CreateDataBase;
import example.common.action.CreateDocs;
import example.common.action.GetDocsCount;
import example.storage.StorageUtil;

@Mapping(CreateDataBase.class)
public class CreateDataBaseHandler extends Handler<CreateDataBase>{
	
	@Inject
	EntityManager em;

	@Override
	public void invoke(CreateDataBase action) throws Exception {
		
		Integer count = subInvoke(new GetDocsCount());
		
		if(count == 0){
			
			ArrayList<String> names = new ArrayList<String>();
			for(int i=0; i < StorageUtil.INIT_DOCS_COUNT; i++){
				names.add("name-"+(i+1));
			}
			
			log.info("insert data...");
			subInvoke(new CreateDocs(names));
		}
	}

}
