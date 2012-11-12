package example.app;

import java.util.List;

import org.junit.Test;

import example.common.app.GetDocsPage;
import example.common.model.Doc;

public class GetDocsPageTest extends AbstractAppTest {
	
	@Test
	public void test_invoke() throws Exception{
		
		List<Doc> list = app.invoke(new GetDocsPage(0, 10)).list;
		assertEquals(10, list.size());
		assertEquals(10, list.get(9).id);
		
		List<Doc> nextList = app.invoke(new GetDocsPage(1, 10)).list;
		assertEquals(10, nextList.size());
		assertEquals(20, nextList.get(9).id);
	}



}
