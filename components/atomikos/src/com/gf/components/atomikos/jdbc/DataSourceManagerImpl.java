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
package com.gf.components.atomikos.jdbc;

import javax.sql.DataSource;

import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
import com.gf.components.jdbc.DataSourceManager;

/**
 * Manager of connections to database
 */
public class DataSourceManagerImpl implements DataSourceManager {

	private AtomikosNonXADataSourceBean ds;
	
	private String user;
	private String password;
	private String driverClass;
	private String url;
	private int poolSize = 5;
	
	
	
	public void init() throws Exception {

		ds = new AtomikosNonXADataSourceBean();
		ds.setUniqueResourceName("TxManager-" + System.currentTimeMillis());

		ds.setUser(user);
		ds.setPassword(password);
		ds.setDriverClassName(driverClass);
		ds.setUrl(url);
		ds.setPoolSize(poolSize);
		// ds.setTestQuery("Select 1");
	}
	
	public void close(){
		ds.close();
	}

	@Override
	public DataSource getDataSource() {
		return ds;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}
	
	

}
