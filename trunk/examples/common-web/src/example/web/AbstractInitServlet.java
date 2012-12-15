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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.gf.core.Engine;
import com.gf.key.TraceHandlers;
import com.gf.log.Log;
import com.gf.log.LogFactory;

import example.app.App;
import example.app.AppImpl;
import example.common.action.CreateDataBase;
import example.storage.Storage;


public abstract class AbstractInitServlet extends HttpServlet {
	
	private static App app;
	
	
	public static App getApp(){
		if(app == null){
			throw new IllegalStateException("app is not inited");
		}
		return app;
	}
	
	Log log = LogFactory.getLog(getClass());
	
	/**
	 * Init the applicaton
	 */
	@Override
	public void init() throws ServletException {
		ServletConfig config = getServletConfig();
		app = createApp(config);
	}

	private App createApp(ServletConfig config) throws ServletException {
		
		Storage storage = createStorage(config);
		
		//create app's engine
		Engine engine = new Engine("App Engine");
		
		//init
		engine.addToContext(storage);
		engine.scanForAnnotations(App.class);
		engine.setConfig(TraceHandlers.class, true);
		
		//invoke actions
		engine.invoke(new CreateDataBase());
		
		//return Application
		return new AppImpl(engine);
	}
	
	protected abstract Storage createStorage(ServletConfig config) throws ServletException;

}
