package jee.web;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import com.gf.service.ActionService;

import example.web.AppServlet;

public class AppJEEServlet extends AppServlet {
	
	@Override
	public void init() throws ServletException {
		ServletConfig servletConfig = getServletConfig();
		setServletConfig(servletConfig);
	}
	
	@EJB(name="AppService")
	public void setAppService(ActionService actionService){
		setApp(actionService);
	}

}
