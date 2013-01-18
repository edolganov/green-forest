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
package jdbc.web;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class InitServletAccessor {
	
	public static DataSource getDataSource(InitServlet servlet) throws SQLException {
		return servlet.dataSourceManager.getDataSource();
	}
	
	public static Connection getConnection(InitServlet servlet) throws SQLException {
		DataSource dataSource = getDataSource(servlet);
		return dataSource.getConnection();
	}

}
