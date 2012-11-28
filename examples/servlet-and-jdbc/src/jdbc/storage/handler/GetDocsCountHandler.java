package jdbc.storage.handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.action.GetDocsCount;

@Mapping(GetDocsCount.class)
public class GetDocsCountHandler extends Handler<GetDocsCount>{
	
	@Inject
	Connection c;

	@Override
	public void invoke(GetDocsCount action) throws Exception {
		
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT count(id) FROM doc");
		rs.next();
		int result = rs.getInt(1);
		st.close();
		
		action.setOutput(result);
		
	}

}
