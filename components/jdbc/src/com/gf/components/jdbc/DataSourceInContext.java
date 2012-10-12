package com.gf.components.jdbc;

import javax.sql.DataSource;

import com.gf.Action;
import com.gf.Filter;
import com.gf.annotation.Inject;
import com.gf.annotation.Order;
import com.gf.service.FilterChain;

@Order(DataSourceInContext.ORDER)
public class DataSourceInContext extends Filter {
	
	public static final int ORDER = -100;
	
	@Inject
	DataSourceManager sourceManager;

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		DataSource dataSource = sourceManager.getDataSource();
		invocationContext.addToInvocationContext(dataSource);
		
		chain.doNext();
		
	}

}
