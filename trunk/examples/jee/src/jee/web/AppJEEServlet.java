package jee.web;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import example.app.IApp;
import example.web.AppServlet;

public class AppJEEServlet extends AppServlet {
	
	@Override
	public void init() throws ServletException {
		ServletConfig servletConfig = getServletConfig();
		setServletConfig(servletConfig);
	}
	
	@EJB(name="App")
	public void setAppService(IApp app){
		setApp(app);
	}

}
