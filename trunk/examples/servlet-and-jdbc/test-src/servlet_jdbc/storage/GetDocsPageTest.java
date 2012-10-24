package servlet_jdbc.storage;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import servlet_jdbc.common.app.GetDocsPage;
import servlet_jdbc.common.model.Doc;

import com.gf.mock.MockInvocationService;

public class GetDocsPageTest extends AbstractStorageTest {
	
	@Test
	public void test_invoke() throws Exception{
		
		Connection c = getConnection();
		initDatabase(c);
		
		GetDocsPage action = new GetDocsPage(0, 10);
		GetDocsPageHandler handler = new GetDocsPageHandler();
		MockInvocationService subInvokeMock = new MockInvocationService();
		subInvokeMock.setSingleReturnValue(30);
		handler.setInvocation(subInvokeMock);
		handler.c = c;
		handler.invoke(action);
		
		List<Doc> list = action.getOutput().list;
		assertEquals(10, list.size());
		assertEquals(10, list.get(9).id);
		
		GetDocsPage nextPage = new GetDocsPage(1, 10);
		handler.invoke(nextPage);
		List<Doc> nextList = nextPage.getOutput().list;
		assertEquals(10, nextList.size());
		assertEquals(20, nextList.get(9).id);
	}



}
