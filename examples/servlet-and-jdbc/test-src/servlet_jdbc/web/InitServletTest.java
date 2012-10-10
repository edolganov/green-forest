package servlet_jdbc.web;

import junit.framework.Assert;

import org.junit.Test;

import servlet_jdbc.app.App;

public class InitServletTest extends Assert {
	
	@Test
	public void test_init_app() throws Exception{
		
		InitServlet servlet = new InitServlet();
		servlet.init(new ServletConfigStub());
		App app = InitServlet.getApp();
		
		assertNotNull(app);
		
	}

}
