package example.servlet_jdbc.view;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import example.servlet_jdbc.model.Application;

public class InitServlet extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Application.init();
	}

}
