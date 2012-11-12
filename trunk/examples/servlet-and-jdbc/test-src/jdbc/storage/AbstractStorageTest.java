package jdbc.storage;

import java.sql.Connection;

import javax.sql.DataSource;

import jdbc.TestUtil;

import com.gf.components.atomikos.jdbc.DataSourceManagerImpl;
import com.gf.components.jdbc.DataSourceManager;
import com.gf.log.Log;
import com.gf.log.LogFactory;

import example.AbstractTest;
import example.common.app.CreateDataBase;
import example.common.app.CreateDocs;

public abstract class AbstractStorageTest extends AbstractTest {
	
	Log log = LogFactory.getLog(getClass());
	DataSourceManagerImpl manager;
	
	public DataSourceManager getDataSourceManager() throws Exception {
		
		if(manager != null){
			return manager;
		}
		
		manager = TestUtil.getDataSourceManager(tmpDirPath, sessionId);
		return manager;
	}
	
	public Connection getConnection() throws Exception {
		DataSourceManager manager = getDataSourceManager();
		DataSource ds = manager.getDataSource();
		Connection c = ds.getConnection();
		return c;
	}
	
	public void initDatabase(Connection c) throws Exception {
		CreateDataBaseHandler createTables = new CreateDataBaseHandler();
		createTables.c = c;
		createTables.invoke(new CreateDataBase());
		
		CreateDocsHandler createDoc = new CreateDocsHandler();
		createDoc.c = c;
		createDoc.invoke(new CreateDocs("doc-1"));
	}

}
