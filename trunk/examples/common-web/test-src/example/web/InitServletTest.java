package example.web;

import java.sql.Connection;
import java.sql.ResultSet;

import org.junit.Test;

import example.app.App;
import example.web.InitServlet;

import servlet_jdbc.storage.CreateDataBaseHandler;

public class InitServletTest extends AbstractWebTest {
	
	
	@Test
	public void test_init_db() throws Exception {
		
		InitServlet servlet = new InitServlet();
		servlet.init(getConfig());
		
		Connection c = getConnection(servlet);
		ResultSet rs = c.createStatement().executeQuery("select count(id) from doc");
		
		rs.next();
		assertEquals(CreateDataBaseHandler.INIT_DOCS_COUNT, rs.getInt(1));
		
		c.close();
	}
	
	
	@Test
	public void test_init_app() throws Exception{
		
		InitServlet servlet = new InitServlet();
		servlet.init(getConfig());
		App app = InitServlet.getApp();
		
		assertNotNull(app);
		
	}


}
