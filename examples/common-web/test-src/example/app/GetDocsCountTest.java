package example.app;

import org.junit.Test;

import example.common.storage.GetDocsCount;
import example.storage.StorageUtil;


public class GetDocsCountTest extends AbstractAppTest {
	
	@Test
	public void test_invoke() throws Exception{
		
		Integer result = app.invoke(new GetDocsCount());
		assertEquals(StorageUtil.INIT_DOCS_COUNT, result+0);
		
	}



}
