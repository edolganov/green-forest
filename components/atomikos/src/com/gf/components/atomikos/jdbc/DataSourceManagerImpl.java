package com.gf.components.atomikos.jdbc;

import javax.sql.DataSource;

import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
import com.gf.components.jdbc.DataSourceManager;

/**
 * Manager of connections to a database and transactions
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
