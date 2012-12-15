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
package jdbc.storage.handler;

import java.sql.Connection;

import javax.sql.DataSource;

import jdbc.TestUtil;
import jdbc.storage.handler.CreateDataBaseHandler;
import jdbc.storage.handler.CreateDocsHandler;

import com.gf.components.atomikos.jdbc.DataSourceManagerImpl;
import com.gf.components.jdbc.DataSourceManager;
import com.gf.log.Log;
import com.gf.log.LogFactory;

import example.AbstractTest;
import example.common.action.CreateDataBase;
import example.common.action.CreateDocs;

public abstract class AbstractStorageTest extends AbstractTest {
	
	Log log = LogFactory.getLog(getClass());
	DataSourceManagerImpl manager;
	
	public DataSourceManager getDataSourceManager() throws Exception {
		
		if(manager != null){
			return manager;
		}
		
		manager = TestUtil.getDataSourceManager(tmpDirPath, sessionId);
		return manager;
	}
	
	public Connection getConnection() throws Exception {
		DataSourceManager manager = getDataSourceManager();
		DataSource ds = manager.getDataSource();
		Connection c = ds.getConnection();
		return c;
	}
	
	public void initDatabase(Connection c) throws Exception {
		CreateDataBaseHandler createTables = new CreateDataBaseHandler();
		createTables.c = c;
		createTables.invoke(new CreateDataBase());
		
		CreateDocsHandler createDoc = new CreateDocsHandler();
		createDoc.c = c;
		createDoc.invoke(new CreateDocs("doc-1"));
	}

}
