package servlet_jdbc.storage;

import java.sql.Connection;
import java.sql.ResultSet;

import org.junit.Test;

import servlet_jdbc.common.storage.CreateOrUpdateDataBase;

public class CreateOrUpdateDataBaseTest extends AbstractStorageTest {
	
	@Test
	public void test_invoke() throws Exception{
		
		Connection c = getConnection();
		
		CreateDataBaseHandler handler = new CreateDataBaseHandler();
		handler.c = c;
		handler.invoke(new CreateOrUpdateDataBase());
		
		//check
		ResultSet rs = c.createStatement().executeQuery("select count(id) from doc");
		rs.next();
		assertEquals(1000, rs.getInt(1));
		
		c.close();
		
	}
	

}
