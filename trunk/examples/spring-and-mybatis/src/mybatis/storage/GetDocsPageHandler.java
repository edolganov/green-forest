package mybatis.storage;

import java.util.List;

import mybatis.mapper.DocMapper;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.app.GetDocsCount;
import example.common.app.GetDocsPage;
import example.common.app.GetDocsPage.Input;
import example.common.model.Doc;
import example.common.model.Page;

@Mapping(GetDocsPage.class)
public class GetDocsPageHandler extends Handler<GetDocsPage>{
	
	@Inject
	DocMapper docMapper;

	@Override
	public void invoke(GetDocsPage action) throws Exception {
		
		Input input = action.input();
		
		int limit = input.limit;
		int pageIndex = input.pageIndex;
		int offset = pageIndex*limit;
		
		List<Doc> list = docMapper.getDocsPage(limit, offset);
		
		Integer count = subInvoke(new GetDocsCount());

		Page<Doc> out = new Page<Doc>(list, pageIndex, limit, count);
		action.setOutput(out);
		
	}

}
