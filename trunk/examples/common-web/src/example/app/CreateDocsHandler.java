package example.app;

import java.util.List;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;
import com.gf.util.Util;

import example.common.app.CheckDocName;
import example.common.app.CreateDocs;
import example.storage.Storage;

@Mapping(CreateDocs.class)
public class CreateDocsHandler extends Handler<CreateDocs>{
	
	@Inject
	Storage storage;

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
		
		storage.equals(action);
		
	}

}
