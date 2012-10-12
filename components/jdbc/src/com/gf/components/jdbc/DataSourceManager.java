package com.gf.components.jdbc;

import javax.sql.DataSource;

/**
 * Manager of connections to a database
 *
 */
public interface DataSourceManager {
	
	DataSource getDataSource();

}
