package servlet_jdbc.app;

import servlet_jdbc.common.app.RenameDoc;
import servlet_jdbc.common.exception.doc.DocEmptyNameException;
import servlet_jdbc.common.exception.doc.DocToLongNameException;
import servlet_jdbc.common.model.Doc;
import servlet_jdbc.storage.Storage;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;
import com.gf.util.Util;

@Mapping(RenameDoc.class)
public class RenameDocHandler extends Handler<RenameDoc>{
	
	@Inject
	Storage storage;

	@Override
	public void invoke(RenameDoc action) throws Exception {
		
		Doc input = action.input();
		int id = input.id;
		String newName = input.name;
		
		//business validation
		if(Util.isEmpty(newName)){
			throw new DocEmptyNameException(id);
		}
		
		int maxNameSize = 20;
		if(newName.length() > maxNameSize){
			throw new DocToLongNameException(id, newName, maxNameSize);
		}
		
		//update db
		storage.invoke(action);
		
	}

}
