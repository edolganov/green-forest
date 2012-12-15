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
import java.sql.Statement;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;

import example.common.action.CreateDataBase;
import example.storage.StorageUtil;


@Mapping(CreateDataBase.class)
public class CreateDataBaseHandler extends Handler<CreateDataBase>{
	
	@Inject
	Connection c;

	@Override
	public void invoke(CreateDataBase action) throws Exception {
				
		if( ! tablesExists()) {
			createTables();
			action.setOutput(true);
		} else {
			action.setOutput(false);
		}
	}

	private void createTables() throws Exception {
		
		log.info("create table 'DOC'");
		Statement st = c.createStatement();
		st.execute("CREATE TABLE IF NOT EXISTS doc (" +
				"id IDENTITY NOT NULL," +
				"name VARCHAR(40) NOT NULL," +
				"PRIMARY KEY (id))");
		st.close();
		
	}
	
	private boolean tablesExists() throws Exception {
	    return StorageUtil.tableExists("doc", c);
	}
	


}
