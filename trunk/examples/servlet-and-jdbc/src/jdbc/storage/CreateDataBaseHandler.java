package jdbc.storage;

import java.sql.Connection;
import java.sql.Statement;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.app.CreateDataBase;
import example.storage.StorageUtil;


@Mapping(CreateDataBase.class)
public class CreateDataBaseHandler extends Handler<CreateDataBase>{
	
	@Inject
	Connection c;

	@Override
	public void invoke(CreateDataBase action) throws Exception {
		
		log.info("Create or update DB structure...");
		
		if( ! tablesExists()) {
			createTables();
		}
		
		log.info("Done");
	}

	private boolean tablesExists() throws Exception {
	    return StorageUtil.tableExists("doc", c);
	}

	private void createTables() throws Exception {
		
		log.info("create table 'DOC'");
		Statement st = c.createStatement();
		st.execute("CREATE TABLE IF NOT EXISTS doc (" +
				"id IDENTITY NOT NULL," +
				"name VARCHAR(40) NOT NULL UNIQUE," +
				"PRIMARY KEY (id))");
		st.close();
		
		
		log.info("insert data...");
		st = c.createStatement();
		for(int i=0; i < StorageUtil.INIT_DOCS_COUNT; i++){
			int num = i+1;
			String name = "name-"+num;
			st.addBatch("INSERT INTO doc (name) VALUES ('"+name+"');");
		}
		st.executeBatch();
		st.close();
	}
	


}
