package servlet_jdbc.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet_jdbc.app.App;
import servlet_jdbc.common.app.GetDocsPage;
import servlet_jdbc.common.app.RenameDoc;
import servlet_jdbc.common.exception.ValidationException;
import servlet_jdbc.common.model.Doc;
import servlet_jdbc.common.model.Page;

import com.gf.util.Util;


public class AppServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private App app;
	
	@Override
	public void init() throws ServletException {
		super.init();
		app = InitServlet.getApp();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int pageIndex = Util.tryParse(req.getParameter("pageIndex"), 0);
		int limit = Util.tryParse(req.getParameter("limit"), 8);

		//get page to view
		Page<Doc> page = app.invoke(new GetDocsPage(pageIndex, limit));
		req.setAttribute("page", page);
		
		showView(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//update doc
		int id = Util.tryParse(req.getParameter("id"), -1);
		String newName = req.getParameter("name");
		
		try {
			app.invoke(new RenameDoc(id, newName));
			req.setAttribute("doc.renamed."+id, Boolean.TRUE);
		}catch (ValidationException e) {
			req.setAttribute("doc.error-key."+id, e.getClass().getSimpleName());
			req.setAttribute("doc.error-obj", e);
		}
		req.setAttribute("doc.newName."+id, newName);
		
		//show a page
		this.doGet(req, resp);
	}
	
	private void showView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewPath = "/WEB-INF/jsp/app.jsp";
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(viewPath);
		requestDispatcher.forward(req, resp);
	}


}
