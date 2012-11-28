package example.app.handler;

import java.util.ArrayList;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.action.CreateDataBase;
import example.common.action.CreateDocs;
import example.storage.Storage;
import example.storage.StorageUtil;

@Mapping(CreateDataBase.class)
public class CreateDataBaseHandler extends Handler<CreateDataBase>{
	
	@Inject
	Storage storage;

	@Override
	public void invoke(CreateDataBase action) throws Exception {
		
		log.info("Create or update DB structure...");
		Boolean created = storage.invoke(action);
		
		if(Boolean.TRUE.equals(created)){
			
			ArrayList<String> names = new ArrayList<String>();
			for(int i=0; i < StorageUtil.INIT_DOCS_COUNT; i++){
				names.add("name-"+(i+1));
			}
			
			log.info("insert data...");
			subInvoke(new CreateDocs(names));
		}
		log.info("Done");
	}

}
