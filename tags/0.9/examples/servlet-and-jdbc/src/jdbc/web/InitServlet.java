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
package jdbc.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import com.gf.components.atomikos.jdbc.DataSourceManagerImpl;
import com.gf.components.atomikos.tx.TxManagerImpl;
import com.gf.components.jdbc.ConnectionInInvoke;
import com.gf.components.jdbc.DataSourceManager;
import com.gf.components.tx.TxManager;
import com.gf.components.tx.UserTransactionInInvoke;
import com.gf.core.Engine;

import example.storage.Storage;
import example.storage.StorageImpl;
import example.web.AbstractInitServlet;

public class InitServlet extends AbstractInitServlet {
	
	DataSourceManager dataSourceManager;

	
	@Override
	protected Storage createStorage(ServletConfig config) throws ServletException {
		
		//create storage's engine
		Engine engine = new Engine("Storage Engine");
		
		//init
		engine.addToContext(createTxManager());
		engine.addToContext(createDataSourceManager(config));
		
		engine.putFilter(UserTransactionInInvoke.class);
		engine.putFilter(ConnectionInInvoke.class);
		
		engine.scanAndPut("jdbc.storage");
		
		//return Storage
		return new StorageImpl(engine);
	}
	
	

	private DataSourceManager createDataSourceManager(ServletConfig config) throws ServletException {
		try {
			DataSourceManagerImpl manager = new DataSourceManagerImpl();
			manager.setUser(config.getInitParameter("db-user"));
			manager.setPassword(config.getInitParameter("db-password"));
			manager.setDriverClass(config.getInitParameter("db-driver"));
			manager.setUrl(config.getInitParameter("db-url"));
			manager.setPoolSize(Integer.parseInt(config.getInitParameter("db-pool-size")));
			manager.init();
			
			this.dataSourceManager = manager;
			
			return manager;
		}catch (Exception e) {
			throw new ServletException("can't init datasource manager", e);
		}

	}

	private TxManager createTxManager() throws ServletException {

		try {
			TxManagerImpl manager = new TxManagerImpl();
			manager.init();
			return manager;
		} catch (Exception e) {
			throw new ServletException("can't init tx manager", e);
		}
		
	}

}
