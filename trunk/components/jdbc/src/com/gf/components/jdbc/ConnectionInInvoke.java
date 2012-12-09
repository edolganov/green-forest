package com.gf.components.jdbc;

import java.sql.Connection;

import javax.sql.DataSource;

import com.gf.Action;
import com.gf.Filter;
import com.gf.annotation.Inject;
import com.gf.annotation.Order;
import com.gf.service.FilterChain;

@Order(Order.SYSTEM_ORDER)
public class ConnectionInInvoke extends Filter {
	
	@Inject
	DataSourceManager sourceManager;

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		DataSource dataSource = sourceManager.getDataSource();
		addToInvocationContext(dataSource);
		
		Connection connection = dataSource.getConnection();
		try {
			
			addToInvocationContext(connection);
			chain.doNext();
			
		}catch (Exception e) {
			throw e;
		} finally {
			
			if( ! connection.isClosed()){
				try {
					connection.close();
				}catch (Exception e) {
					log.error("can't close connection", e);
				}
			}
		}
		
	}

}
