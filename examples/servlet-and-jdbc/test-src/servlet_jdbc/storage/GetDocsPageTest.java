package servlet_jdbc.storage;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;


import com.gf.core.Engine;

import example.common.app.GetDocsPage;
import example.common.model.Doc;

public class GetDocsPageTest extends AbstractStorageTest {
	
	@Test
	public void test_invoke() throws Exception{
		
		Connection c = getConnection();
		initDatabase(c);
		
		Engine engine = new Engine();
		engine.putHandler(GetDocsPageHandler.class);
		engine.putHandler(GetDocsCountHandler.class);
		engine.addToContext(c);
		
		List<Doc> list = engine.invoke(new GetDocsPage(0, 10)).list;
		assertEquals(10, list.size());
		assertEquals(10, list.get(9).id);
		
		List<Doc> nextList = engine.invoke(new GetDocsPage(1, 10)).list;
		assertEquals(10, nextList.size());
		assertEquals(20, nextList.get(9).id);
	}



}
