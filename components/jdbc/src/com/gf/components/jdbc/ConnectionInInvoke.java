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
