package jdbc.web;

import java.sql.Connection;
import java.sql.ResultSet;

import jdbc.TestUtil;

import org.junit.Test;

import example.AbstractTest;
import example.app.App;
import example.storage.StorageUtil;


public class InitServletTest extends AbstractTest {
	
	
	@Test
	public void test_init_db() throws Exception {
		
		InitServlet servlet = new InitServlet();
		servlet.init(TestUtil.getConfig(tmpDirPath, sessionId));
		
		Connection c = InitServletAccessor.getConnection(servlet);
		ResultSet rs = c.createStatement().executeQuery("select count(id) from doc");
		
		rs.next();
		assertEquals(StorageUtil.INIT_DOCS_COUNT, rs.getInt(1));
		
		c.close();
	}
	
	
	@Test
	public void test_init_app() throws Exception{
		
		InitServlet servlet = new InitServlet();
		servlet.init(TestUtil.getConfig(tmpDirPath, sessionId));
		App app = InitServlet.getApp();
		
		assertNotNull(app);
		
	}


}
