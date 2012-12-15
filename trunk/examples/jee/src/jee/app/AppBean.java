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
package jee.app;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.gf.components.jee.ActionServiceBean;
import com.gf.core.Engine;
import com.gf.key.TraceHandlers;
import com.gf.service.ActionService;

import example.app.App;
import example.common.action.CreateDataBase;
import example.storage.Storage;

@Stateless(name="App")
@Local(App.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class AppBean extends ActionServiceBean implements App {
	
	@EJB(name="Storage")
	Storage storage;
	
	
	@Override
	public ActionService createActionService() {
		
		//create app's engine
		Engine engine = new Engine("App Engine");
		
		//init
		engine.addToContext(storage);
		engine.scanForAnnotations(App.class);
		engine.setConfig(TraceHandlers.class, true);
		
		//invoke actions
		engine.invoke(new CreateDataBase());

		return engine;
	}



}