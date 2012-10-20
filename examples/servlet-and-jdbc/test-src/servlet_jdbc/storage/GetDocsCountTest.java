package servlet_jdbc.storage;

import java.sql.Connection;

import org.junit.Test;

import servlet_jdbc.common.storage.GetDocsCount;

public class GetDocsCountTest extends AbstractStorageTest {
	
	@Test
	public void test_invoke() throws Exception{
		
		Connection c = getConnection();
		initDatabase(c);
		
		GetDocsCount action = new GetDocsCount();
		GetDocsCountHandler handler = new GetDocsCountHandler();
		handler.c = c;
		handler.invoke(action);
		
		assertEquals(CreateDataBaseHandler.INIT_DOCS_COUNT, action.getOutput()+0);
		
	}



}
