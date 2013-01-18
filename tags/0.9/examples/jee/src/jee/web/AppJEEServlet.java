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
package jee.web;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import example.app.App;
import example.common.exception.ValidationException;
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
	
	@Override
	protected ValidationException tryConvert(RuntimeException e) {
		if(e instanceof EJBException){
			Exception ex = ((EJBException)e).getCausedByException();
			if(ex instanceof ValidationException){
				return (ValidationException)ex;
			}
		}
		return null;
	}
	

}
