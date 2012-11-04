package example.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gf.extra.invocation.Trace;
import com.gf.key.core.TraceHandlers;
import com.gf.util.Util;

import example.app.App;
import example.common.app.GetDocsPage;
import example.common.app.RenameDoc;
import example.common.exception.ValidationException;
import example.common.model.Doc;
import example.common.model.Page;


public class AppServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private App app;
	
	@Override
	public void init() throws ServletException {
		super.init();
		app = AbstractInitServlet.getApp();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int pageIndex = Util.tryParse(req.getParameter("pageIndex"), 0);
		int limit = Util.tryParse(req.getParameter("limit"), 8);

		//get page to view
		GetDocsPage action = new GetDocsPage(pageIndex, limit);
		Page<Doc> page = app.invoke(action);
		Trace trace = TraceHandlers.getTrace(action);
		
		req.setAttribute("page", page);
		req.setAttribute("selectTrace", trace);
		
		showView(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//update doc
		int id = Util.tryParse(req.getParameter("id"), -1);
		String newName = req.getParameter("name");
		
		RenameDoc action = new RenameDoc(id, newName);
		try {
			app.invoke(action);
			req.setAttribute("doc.renamed."+id, Boolean.TRUE);
		}catch (ValidationException e) {
			req.setAttribute("doc.error-key."+id, e.getClass().getSimpleName());
			req.setAttribute("doc.error-obj."+id, e);
		}
		req.setAttribute("doc.newName."+id, newName);
		
		Trace updateTrace = TraceHandlers.getTrace(action);
		req.setAttribute("updateTrace", updateTrace);
		
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
