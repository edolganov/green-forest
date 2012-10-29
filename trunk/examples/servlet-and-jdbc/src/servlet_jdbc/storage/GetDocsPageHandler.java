package servlet_jdbc.storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.app.GetDocsPage;
import example.common.app.GetDocsPage.Input;
import example.common.model.Doc;
import example.common.model.Page;
import example.common.storage.GetDocsCount;

@Mapping(GetDocsPage.class)
public class GetDocsPageHandler extends Handler<GetDocsPage>{

	@Inject
	Connection c;
	
	@Override
	public void invoke(GetDocsPage action) throws Exception {
		
		Input input = action.input();
		
		int limit = input.limit;
		int pageIndex = input.pageIndex;
		int offset = pageIndex*limit;
		
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM doc LIMIT "+limit+" OFFSET "+offset);
		
		ArrayList<Doc> list = new ArrayList<Doc>();
		while(rs.next()){
			list.add(convert(rs));
		}
		st.close();
		
		Integer count = subInvoke(new GetDocsCount());
		
		
		Page<Doc> out = new Page<Doc>(list, pageIndex, limit, count);
		action.setOutput(out);
		
	}

	private Doc convert(ResultSet rs) throws Exception {
		Doc doc = new Doc();
		doc.id = rs.getInt(1);
		doc.name = rs.getString(2);
		return doc;
	}

}
