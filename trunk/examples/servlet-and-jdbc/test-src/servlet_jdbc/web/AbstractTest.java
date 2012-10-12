package servlet_jdbc.web;

import javax.servlet.ServletConfig;

import org.junit.After;

import junit.framework.Assert;

public abstract class AbstractTest extends Assert {
	
	public final String sessionId = String.valueOf(System.currentTimeMillis());
	public final String tmpDirPath = "./tmp";
	
	public ServletConfig getConfig() {
		
		ServletConfigMock config = new ServletConfigMock();
		config.initPatams.put("db-user", "sa");
		config.initPatams.put("db-password", "");
		config.initPatams.put("db-driver", "org.h2.Driver");
		config.initPatams.put("db-url", "jdbc:h2:"+tmpDirPath+"/db-"+sessionId);
		config.initPatams.put("db-pool-size", "5");
		return config;
	}
	
	@After
	public void after(){
		//new File(tmpDirPath);
	}
	
	

}
