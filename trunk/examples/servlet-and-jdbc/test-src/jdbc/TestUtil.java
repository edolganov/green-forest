package jdbc;

import javax.servlet.ServletConfig;

import com.gf.components.atomikos.jdbc.DataSourceManagerImpl;

import example.web.ServletConfigMock;

public class TestUtil {
	
	public static DataSourceManagerImpl getDataSourceManager(String tmpDirPath, String sessionId) throws Exception {
		
		DataSourceManagerImpl manager = new DataSourceManagerImpl();
		manager.setUser("sa");
		manager.setPassword("");
		manager.setDriverClass("org.h2.Driver");
		manager.setUrl("jdbc:h2:"+tmpDirPath+"/db-"+sessionId);
		manager.setPoolSize(10);
		manager.init();
		return manager;
	}
	
	public static ServletConfig getConfig(String tmpDirPath, String sessionId) {
		
		ServletConfigMock config = new ServletConfigMock();
		config.initPatams.put("db-user", "sa");
		config.initPatams.put("db-password", "");
		config.initPatams.put("db-driver", "org.h2.Driver");
		config.initPatams.put("db-url", "jdbc:h2:"+tmpDirPath+"/db-"+sessionId);
		config.initPatams.put("db-pool-size", "5");
		return config;
	}

}
