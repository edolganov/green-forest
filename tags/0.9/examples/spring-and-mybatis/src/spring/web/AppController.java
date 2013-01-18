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
import example.common.action.CreateDataBase;
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
