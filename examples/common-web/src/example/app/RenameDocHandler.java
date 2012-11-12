package example.app;


import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.app.CheckDocName;
import example.common.app.RenameDoc;
import example.common.model.Doc;
import example.storage.Storage;

@Mapping(RenameDoc.class)
public class RenameDocHandler extends Handler<RenameDoc>{
	
	@Inject
	Storage storage;

	@Override
	public void invoke(RenameDoc action) throws Exception {
		
		Doc input = action.input();
		
		//validation
		subInvoke(new CheckDocName(input));
		
		//update db
		storage.invoke(action);
		
	}

}
