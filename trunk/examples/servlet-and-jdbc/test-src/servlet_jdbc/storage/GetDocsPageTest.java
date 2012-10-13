package servlet_jdbc.storage;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import servlet_jdbc.common.app.GetDocsPage;
import servlet_jdbc.common.model.Doc;

public class GetDocsPageTest extends AbstractStorageTest {
	
	@Test
	public void test_invoke() throws Exception{
		
		Connection c = getConnection();
		createTable(c);
		
		GetDocsPage action = new GetDocsPage(0, 100);
		GetDocsPageHandler handler = new GetDocsPageHandler();
		handler.c = c;
		handler.invoke(action);
		
		List<Doc> list = action.getOutput();
		assertEquals(100, list.size());
		assertEquals(100, list.get(99).id);
		
		GetDocsPage nextPage = new GetDocsPage(1, 100);
		handler.invoke(nextPage);
		List<Doc> nextList = nextPage.getOutput();
		assertEquals(100, nextList.size());
		assertEquals(200, nextList.get(99).id);
	}



}
