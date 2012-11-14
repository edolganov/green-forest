package mybatis.storage;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;

import mybatis.mapper.DocMapper;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;
import com.gf.components.mybatis.SqlSessionSettings;

import example.common.app.CreateDocs;
import example.common.model.Doc;

@SqlSessionSettings(execType=ExecutorType.BATCH)
@Mapping(CreateDocs.class)
public class CreateDocsHandler extends Handler<CreateDocs>{
	
	@Inject
	DocMapper docMapper;

	@Override
	public void invoke(CreateDocs action) throws Exception {
		
		List<String> names = action.input();
		List<Doc> out = new ArrayList<Doc>();
		
		for (String name : names) {
			int id = docMapper.nextDocId();
			docMapper.createDoc(id, name);
			out.add(new Doc(id, name));
		}
		
		action.setOutput(out);
		
	}

}
