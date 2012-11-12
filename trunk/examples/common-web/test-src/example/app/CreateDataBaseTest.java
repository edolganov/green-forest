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
