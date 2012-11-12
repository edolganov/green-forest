package example.app;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.app.CreateDataBase;
import example.storage.Storage;

@Mapping(CreateDataBase.class)
public class CreateDataBaseHandler extends Handler<CreateDataBase>{
	
	@Inject
	Storage storage;

	@Override
	public void invoke(CreateDataBase action) throws Exception {
		
		Boolean created = storage.invoke(action);
		if(Boolean.TRUE.equals(created)){
			
		}
	}

}
