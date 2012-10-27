package servlet_jdbc.app;

import servlet_jdbc.common.app.RenameDoc;
import servlet_jdbc.common.exception.doc.DocEmptyNameException;

import com.gf.Handler;
import com.gf.annotation.Mapping;

@Mapping(RenameDoc.class)
public class RenameDocHandler extends Handler<RenameDoc>{

	@Override
	public void invoke(RenameDoc action) throws Exception {
		//TODO
		throw new DocEmptyNameException(-1);
	}

}
