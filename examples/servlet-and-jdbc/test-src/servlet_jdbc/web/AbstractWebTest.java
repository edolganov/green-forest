package servlet_jdbc.web;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletConfig;

import servlet_jdbc.AbstractTest;

public abstract class AbstractWebTest extends AbstractTest {
		
	
	public ServletConfig getConfig() {
		
		ServletConfigMock config = new ServletConfigMock();
		config.initPatams.put("db-user", "sa");
		config.initPatams.put("db-password", "");
		config.initPatams.put("db-driver", "org.h2.Driver");
		config.initPatams.put("db-url", "jdbc:h2:"+tmpDirPath+"/db-"+sessionId);
		config.initPatams.put("db-pool-size", "5");
		return config;
	}
	

	public Connection getConnection(InitServlet servlet) throws SQLException {
		return servlet.dataSourceManager.getDataSource().getConnection();
	}


	
	
	
	

}
