package jdbc.storage.handler;

import java.sql.Connection;

import jdbc.storage.handler.GetDocsCountHandler;
import jdbc.storage.handler.GetDocsPageHandler;
import jdbc.storage.handler.RenameDocHandler;


import org.junit.Test;

import test.model.ThrowExceptionAfterHandler;

import com.gf.core.Engine;
import com.gf.exception.TestRuntimeException;

import example.common.action.GetDocsPage;
import example.common.action.RenameDoc;
import example.common.model.Doc;

public class TxExtTest extends AbstractStorageTest {
	
	
	@Test
	public void test_rollback_without_manager() throws Exception{
		
		Connection c = getConnection();
		initDatabase(c);
		
		Engine engine = new Engine();
		engine.putHandler(RenameDocHandler.class);
		engine.putInterceptor(ThrowExceptionAfterHandler.class);
		engine.addToContext(c);
		
		int id = 1;
		String newName = "new123";
		try {
			engine.invoke(new RenameDoc(id, newName));
			fail("ex exp");
		}catch (TestRuntimeException e) {
			//ok
		}
		
		Engine engine2 = new Engine();
		engine2.putHandler(GetDocsPageHandler.class);
		engine2.putHandler(GetDocsCountHandler.class);
		engine2.addToContext(c);
		Doc result = engine2.invoke(new GetDocsPage(0, 1)).list.get(0);
		assertEquals(newName, result.name);
		
	}

}
