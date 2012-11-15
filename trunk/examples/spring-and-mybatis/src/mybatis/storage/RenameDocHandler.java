package mybatis.storage;

import mybatis.mapper.DocMapper;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.app.RenameDoc;
import example.common.model.Doc;

@Mapping(RenameDoc.class)
public class RenameDocHandler extends Handler<RenameDoc>{
	
	@Inject
	DocMapper docMapper;

	@Override
	public void invoke(RenameDoc action) throws Exception {
		
		Doc input = action.input();
		long id = input.id;
		String newName = input.name;
		
		docMapper.renameDoc(id, newName);
		
	}

}
