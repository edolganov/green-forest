package servlet_jdbc.storage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import servlet_jdbc.common.storage.GetDocsCount;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

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
