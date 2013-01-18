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
