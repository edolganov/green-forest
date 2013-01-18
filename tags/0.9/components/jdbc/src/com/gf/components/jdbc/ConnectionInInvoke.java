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
import com.gf.core.components.UserTxAndConnection;
import com.gf.service.FilterChain;

/**
 * Filter for adding {@link Connection} into handler's context.
 * The filter opens a connection before handler and finally closes it.
 * The filter rollbacks the connection by any exception if there is no opened UserTransaction 
 * by {@link com.gf.components.tx.UserTransactionInInvoke}.
 * Need {@link DataSourceManager} for work.
 * <br>Example:
 * <pre>
 * //impl of DataSourceManager
 * import javax.sql.DataSource;
 * 
 * public class DataSourceManagerImpl implements DataSourceManager {
 * 
 *   public DataSource getDataSource() {
 *     return dataSource;
 *   }
 * 
 * }
 * 
 * //engine
 * DataSourceManagerImpl dataSourceManagerImpl = ...;
 * Engine engine = new Engine();
 * engine.addToContext(dataSourceManagerImpl);
 * engine.putFilter(ConnectionInInvoke.class);
 * engine.putHandler(SomeHandler.class);
 * 
 * 
 * //handler
 * import java.sql.Connection;
 * 
 * &#064;Mapping(SomeAction.class)
 * public class SomeHandler extends Handler&lt;SomeAction&gt;{
 *   
 *   &#064;Inject
 *   Connection connection;
 * 
 *   public void invoke(SomeAction action) throws Exception {
 *     ...
 *   }
 * 
 * }
 * </pre>
 * @author Evgeny Dolganov
 *
 */
@Order(UserTxAndConnection.ORDER_OF_CONN)
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
			
			//rollback if there is no ongoing UserTransaction in invoke
			if( ! action.containsAttr(UserTxAndConnection.OPENED_USER_TX_FLAG)){
				connection.rollback();
			}
			
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
