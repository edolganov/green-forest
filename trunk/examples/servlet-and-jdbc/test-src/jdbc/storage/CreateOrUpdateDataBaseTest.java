package jdbc.storage;

import java.sql.Connection;
import java.sql.ResultSet;

import jdbc.storage.CreateDataBaseHandler;

import org.junit.Test;

import com.gf.core.Engine;

import example.common.app.CreateDataBase;
import example.storage.StorageUtil;


public class CreateOrUpdateDataBaseTest extends AbstractStorageTest {
	
	@Test
	public void test_invoke() throws Exception{
		
		Connection c = getConnection();
		
		Engine engine = new Engine();
		engine.putHandler(CreateDataBaseHandler.class);
		engine.addToContext(c);
		
		engine.invoke(new CreateDataBase());
		
		//check
		ResultSet rs = c.createStatement().executeQuery("select count(id) from doc");
		rs.next();
		assertEquals(StorageUtil.INIT_DOCS_COUNT, rs.getInt(1));
		
		c.close();
		
	}
	

}
