/*
 * Copyright 2012 Evgeny Dolganov (evgenij.dolganov@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.web;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gf.extra.trace.Trace;
import com.gf.key.TraceHandlers;
import com.gf.log.Log;
import com.gf.log.LogFactory;
import com.gf.util.Util;

import example.app.App;
import example.common.action.GetDocsPage;
import example.common.action.RenameDoc;
import example.common.exception.ValidationException;
import example.common.model.Doc;
import example.common.model.Page;


public class AppServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private transient ServletConfig config;
	private Log log = LogFactory.getLog(getClass());
	private App app;
	private Map<Object, Object> labels = Collections.emptyMap();
	
	@Override
	public void init() throws ServletException {
		this.config = getServletConfig();
		app = AbstractInitServlet.getApp();
		initLabels();
	}
	
	/** for manually init */
	public void setApp(App app){
		this.app = app;
	}
	
	/** for manually init */
	public void setServletConfig(ServletConfig config){
		this.config = config;
		initLabels();
	}
	
	
	private void initLabels() {
		String path = config.getServletContext().getRealPath("./WEB-INF/labels.properties");
		File file = new File(path);
		if(file.exists()){
			try {
				Properties props = new Properties();
				props.load(new FileReader(file));
				labels = props;
			}catch (Exception e) {
				log.error("can't load labels file", e);
			}
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int pageIndex = Util.tryParse(req.getParameter("pageIndex"), 0);
		int limit = Util.tryParse(req.getParameter("limit"), 8);

		//get page to view
		GetDocsPage action = new GetDocsPage(pageIndex, limit);
		Page<Doc> page = app.invoke(action);
		Trace trace = TraceHandlers.getTrace(action);
		
		req.setAttribute("label", labels);
		req.setAttribute("page", page);
		req.setAttribute("selectTrace", trace);
		
		showView(req, resp);
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//update doc
		int id = Util.tryParse(req.getParameter("id"), -1);
		String newName = req.getParameter("name");
		
		RenameDoc action = new RenameDoc(id, newName);
		try {
			app.invoke(action);
			req.setAttribute("doc.renamed."+id, Boolean.TRUE);
		}catch (RuntimeException e) {
			ValidationException ve = tryConvert(e);
			if(ve != null){
				req.setAttribute("doc.error-key."+id, ve.getClass().getSimpleName());
				req.setAttribute("doc.error-obj."+id, ve);
			} else {
				throw e;
			}
		}
		
		req.setAttribute("doc.newName."+id, newName);
		
		Trace updateTrace = TraceHandlers.getTrace(action);
		req.setAttribute("updateTrace", updateTrace);
		
		//show a page
		this.doGet(req, resp);
	}

	/** override if need */
	protected ValidationException tryConvert(RuntimeException e) {
		if(e instanceof ValidationException){
			return (ValidationException) e;
		}
		return null;
	}
	
	private void showView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewPath = "/WEB-INF/jsp/app.jsp";
		ServletContext servletContext = config.getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(viewPath);
		requestDispatcher.forward(req, resp);
	}


}
