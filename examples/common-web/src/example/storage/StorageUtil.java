package example.storage;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class StorageUtil {
	
	public static final int DOC_NAME_SIZE = 40;
	public static final int INIT_DOCS_COUNT = 28;
	
	
	
	public static boolean tableExists(String tableName, Connection c) throws Exception {
		DatabaseMetaData dbm = c.getMetaData();
	    return tableExists(tableName, dbm);
	}
	
	
	public static boolean tableExists(String tableName, DatabaseMetaData dbm) throws Exception {
	    ResultSet rs = dbm.getTables(null, null, tableName.toUpperCase(), null);
	    return rs.next();
	}

}
