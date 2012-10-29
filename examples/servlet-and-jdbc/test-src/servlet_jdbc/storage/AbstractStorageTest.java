package servlet_jdbc.storage;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.sql.DataSource;

import servlet_jdbc.AbstractTest;
import servlet_jdbc.common.storage.CreateOrUpdateDataBase;

import com.gf.components.atomikos.jdbc.DataSourceManagerImpl;
import com.gf.components.jdbc.DataSourceManager;

public abstract class AbstractStorageTest extends AbstractTest {
	
	DataSourceManagerImpl manager;
	
	public DataSourceManager createDataSourceManager() throws Exception {
		
		if(manager != null){
			return manager;
		}
		
		manager = new DataSourceManagerImpl();
		manager.setUser("sa");
		manager.setPassword("");
		manager.setDriverClass("org.h2.Driver");
		manager.setUrl("jdbc:h2:"+tmpDirPath+"/db-"+sessionId);
		manager.setPoolSize(1);
		manager.init();
		return manager;
	}
	
	public Connection getConnection() throws Exception {
		DataSourceManager manager = createDataSourceManager();
		DataSource ds = manager.getDataSource();
		Connection c = ds.getConnection();
		return c;
	}
	
	public void initDatabase(Connection c) throws Exception {
		CreateDataBaseHandler createTables = new CreateDataBaseHandler();
		createTables.c = c;
		createTables.invoke(new CreateOrUpdateDataBase());
	}
	
	public String getDocName(Connection c, int id) throws Exception {
		ResultSet rs = c.createStatement().executeQuery("select name from doc where id="+id);
		rs.next();
		String out = rs.getString(1);
		rs.close();
		return out;
	}

}
