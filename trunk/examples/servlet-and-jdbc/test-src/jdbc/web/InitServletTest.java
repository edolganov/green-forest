package jdbc.web;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;

import org.junit.Test;

import example.AbstractTest;
import example.app.App;
import example.storage.StorageUtil;
import example.web.AbstractInitServlet;
import example.web.ServletConfigMock;


public class InitServletTest extends AbstractTest {
	
	
	@Test
	public void test_init_db() throws Exception {
		
		InitServlet servlet = new InitServlet();
		servlet.init(getConfig());
		
		Connection c = getConnection(servlet);
		ResultSet rs = c.createStatement().executeQuery("select count(id) from doc");
		
		rs.next();
		assertEquals(StorageUtil.INIT_DOCS_COUNT, rs.getInt(1));
		
		c.close();
	}
	
	
	@Test
	public void test_init_app() throws Exception{
		
		InitServlet servlet = new InitServlet();
		servlet.init(getConfig());
		App app = AbstractInitServlet.getApp();
		
		assertNotNull(app);
		
	}
	
	
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
