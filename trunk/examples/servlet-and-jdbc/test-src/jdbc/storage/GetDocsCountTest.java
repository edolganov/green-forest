package jdbc.storage;

import java.sql.Connection;

import jdbc.storage.GetDocsCountHandler;

import org.junit.Test;

import com.gf.core.Engine;

import example.common.storage.GetDocsCount;
import example.storage.StorageUtil;


public class GetDocsCountTest extends AbstractStorageTest {
	
	@Test
	public void test_invoke() throws Exception{
		
		Connection c = getConnection();
		initDatabase(c);
		
		Engine engine = new Engine();
		engine.putHandler(GetDocsCountHandler.class);
		engine.addToContext(c);
		
		Integer result = engine.invoke(new GetDocsCount());
		assertEquals(StorageUtil.INIT_DOCS_COUNT, result+0);
		
	}



}
