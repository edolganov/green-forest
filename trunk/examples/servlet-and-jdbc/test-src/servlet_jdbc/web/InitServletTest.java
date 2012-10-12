package servlet_jdbc.web;

import org.junit.Test;

import servlet_jdbc.app.App;

public class InitServletTest extends AbstractTest {
	
	
	@Test
	public void test_init_db() throws Exception {
		
		InitServlet servlet = new InitServlet();
		servlet.init(getConfig());
		
		
		
		
	}



	
	
	@Test
	public void test_init_app() throws Exception{
		
		InitServlet servlet = new InitServlet();
		servlet.init(getConfig());
		App app = InitServlet.getApp();
		
		assertNotNull(app);
		
	}


}
