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
package jdbc;

import javax.servlet.ServletConfig;

import com.gf.components.atomikos.jdbc.DataSourceManagerImpl;

import example.web.ServletConfigMock;

public class TestUtil {
	
	public static DataSourceManagerImpl getDataSourceManager(String tmpDirPath, String sessionId) throws Exception {
		
		DataSourceManagerImpl manager = new DataSourceManagerImpl();
		manager.setUser("sa");
		manager.setPassword("");
		manager.setDriverClass("org.h2.Driver");
		manager.setUrl("jdbc:h2:"+tmpDirPath+"/db-"+sessionId);
		manager.setPoolSize(10);
		manager.init();
		return manager;
	}
	
	public static ServletConfig getConfig(String tmpDirPath, String sessionId) {
		
		ServletConfigMock config = new ServletConfigMock();
		config.initPatams.put("db-user", "sa");
		config.initPatams.put("db-password", "");
		config.initPatams.put("db-driver", "org.h2.Driver");
		config.initPatams.put("db-url", "jdbc:h2:"+tmpDirPath+"/db-"+sessionId);
		config.initPatams.put("db-pool-size", "5");
		return config;
	}

}
