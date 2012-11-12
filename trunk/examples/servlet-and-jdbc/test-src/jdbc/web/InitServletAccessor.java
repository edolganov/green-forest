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
