package spring.web;

import javax.annotation.PostConstruct;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import example.app.App;
import example.common.app.CreateDataBase;
import example.web.AppServlet;

@Controller
public class AppController {
	
	@Autowired
	App app;
	
	@Autowired
	ServletConfig config;
	
	AppServlet delegate;
	
	@PostConstruct
	public void init(){
		
		app.invoke(new CreateDataBase());
		
		delegate = new AppServlet();
		delegate.setServletConfig(config);
		delegate.setApp(app);
	}
	
	@RequestMapping(value="/app", method=RequestMethod.GET)
	public void getPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		delegate.doGet(req, resp);
	}
	
	
	@RequestMapping(value="/app", method=RequestMethod.POST)
	public void renameDoc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		delegate.doPost(req, resp);
	}
	
	

}
