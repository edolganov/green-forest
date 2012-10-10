package servlet_jdbc.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet_jdbc.app.App;
import servlet_jdbc.common.app.GetDocsPage;
import servlet_jdbc.common.model.Doc;


public class AppServlet extends HttpServlet {
	
	private App app;
	
	@Override
	public void init() throws ServletException {
		super.init();
		app = InitServlet.getApp();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Doc> docs = app.invoke(new GetDocsPage());
		req.setAttribute("docs", docs);
		
		showView(req, resp);
		
	}
	

	private void showView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewPath = "/WEB-INF/jsp/app.jsp";
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(viewPath);
		requestDispatcher.forward(req, resp);
	}

}
