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
package example.app;

import java.sql.Connection;
import java.sql.ResultSet;

import org.junit.Test;

import example.storage.StorageUtil;

public class CreateDataBaseTest extends AbstractAppTest {
	
	@Test
	public void test_data_base_created() throws Exception{
		
		Connection c = ds.getConnection();
		ResultSet rs = c.createStatement().executeQuery("select count(id) from doc");
		rs.next();
		assertEquals(StorageUtil.INIT_DOCS_COUNT, rs.getInt(1));
		c.close();
		
	}

}
