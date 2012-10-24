package servlet_jdbc.storage;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

import servlet_jdbc.common.storage.CreateOrUpdateDataBase;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;


@Mapping(CreateOrUpdateDataBase.class)
public class CreateDataBaseHandler extends Handler<CreateOrUpdateDataBase>{
	
	public static final int INIT_DOCS_COUNT = 30;
	
	@Inject
	Connection c;

	@Override
	public void invoke(CreateOrUpdateDataBase action) throws Exception {
		
		log.info("Create or update DB structure...");
		
		if( ! tablesExists()) {
			createTables();
		}
		
		log.info("Done");
	}

	private boolean tablesExists() throws Exception {
		DatabaseMetaData dbm = c.getMetaData();
	    return tableExists("doc", dbm);
	}

	private void createTables() throws Exception {
		
		log.info("create table 'DOC'");
		Statement st = c.createStatement();
		st.execute("CREATE TABLE IF NOT EXISTS doc (" +
				"id INT NOT NULL," +
				"name VARCHAR(40) NOT NULL UNIQUE," +
				"PRIMARY KEY (id))");
		st.close();
		
		
		log.info("insert data...");
		st = c.createStatement();
		for(int i=0; i < INIT_DOCS_COUNT; i++){
			int num = i+1;
			String name = "name-"+num;
			st.addBatch("INSERT INTO doc VALUES ("+num+", '"+name+"');");
		}
		st.executeBatch();
		st.close();
	}
	
	private boolean tableExists(String tableName, DatabaseMetaData dbm) throws Exception {
	    ResultSet rs = dbm.getTables(null, null, tableName.toUpperCase(), null);
	    return rs.next();
	}

}
