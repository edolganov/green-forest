package example.app;

import com.gf.Handler;
import com.gf.annotation.Mapping;
import com.gf.util.Util;

import example.common.app.CheckDocName;
import example.common.exception.doc.DocEmptyNameException;
import example.common.exception.doc.DocToLongNameException;
import example.common.model.Doc;

@Mapping(CheckDocName.class)
public class CheckDocNameHandler extends Handler<CheckDocName>{

	@Override
	public void invoke(CheckDocName action) throws Exception {
		
		Doc doc = action.input();
		long id = doc.id;
		String name = doc.name;
		
		if(Util.isEmpty(name)){
			throw new DocEmptyNameException(id);
		}
		
		int maxNameSize = 20;
		if(name.length() > maxNameSize){
			throw new DocToLongNameException(id, name, maxNameSize);
		}
		
	}

}
