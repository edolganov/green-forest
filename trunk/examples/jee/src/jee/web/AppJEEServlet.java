package jee.web;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import example.app.App;
import example.web.AppServlet;

public class AppJEEServlet extends AppServlet {
	
	@Override
	public void init() throws ServletException {
		ServletConfig servletConfig = getServletConfig();
		setServletConfig(servletConfig);
	}
	
	@EJB(name="App")
	public void setAppService(App app){
		setApp(app);
	}

}
