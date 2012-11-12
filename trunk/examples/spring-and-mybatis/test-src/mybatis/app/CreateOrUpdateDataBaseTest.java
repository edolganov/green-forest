package mybatis.app;

import java.sql.Connection;
import java.sql.ResultSet;

import org.junit.Test;

import example.common.app.CreateOrUpdateDataBase;
import example.storage.StorageUtil;

public class CreateOrUpdateDataBaseTest extends AbstractSpringTest {
	
	
	@Test
	public void test_invoke() throws Exception{
		
		app.invoke(new CreateOrUpdateDataBase());
		
		Connection c = ds.getConnection();
		ResultSet rs = c.createStatement().executeQuery("select count(id) from doc");
		rs.next();
		assertEquals(StorageUtil.INIT_DOCS_COUNT, rs.getInt(1));
		c.close();
		
		
	}

}
