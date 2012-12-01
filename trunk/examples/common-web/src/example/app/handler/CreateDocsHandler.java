package example.app.handler;

import java.util.List;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;
import com.gf.util.Util;

import example.common.action.CheckDocName;
import example.common.action.CreateDocs;
import example.storage.IStorage;

@Mapping(CreateDocs.class)
public class CreateDocsHandler extends Handler<CreateDocs>{
	
	@Inject
	IStorage storage;

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
		
		storage.invoke(action);
		
	}

}
