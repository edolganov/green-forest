package mybatis.storage;

import mybatis.mapper.DocMapper;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.app.GetDocsCount;

@Mapping(GetDocsCount.class)
public class GetDocsCountHandler extends Handler<GetDocsCount>{
	
	@Inject
	DocMapper docMapper;

	@Override
	public void invoke(GetDocsCount action) throws Exception {
		
		int count = docMapper.getDocsCount();
		action.setOutput(count);
		
	}

}
