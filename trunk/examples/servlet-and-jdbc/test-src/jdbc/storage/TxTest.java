package jdbc.storage;

import java.sql.Connection;

import jdbc.storage.model.ThrowExceptionAfterHandler;

import org.junit.Test;

import com.gf.components.atomikos.tx.TxManagerImpl;
import com.gf.components.jdbc.ConnectionInInvoke;
import com.gf.components.tx.UserTransactionInInvoke;
import com.gf.core.Engine;
import com.gf.exception.TestRuntimeException;

import example.common.app.GetDocsPage;
import example.common.app.RenameDoc;
import example.common.model.Doc;

public class TxTest extends AbstractStorageTest {
	
	
	@Test
	public void test_rollback_with_manager() throws Exception{
		
		Connection c = getConnection();
		initDatabase(c);
		
		TxManagerImpl manager = new TxManagerImpl();
		manager.init();
		
		Engine engine = new Engine();
		engine.addToContext(manager);
		engine.addToContext(getDataSourceManager());
		engine.putFilter(UserTransactionInInvoke.class);
		engine.putFilter(ConnectionInInvoke.class);
		engine.putHandler(RenameDocHandler.class);
		engine.putInterceptor(ThrowExceptionAfterHandler.class);
		
		int id = 1;
		String newName = "new123";
		try {
			log.info("call rename");
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
		assertFalse(newName.equals(result.name));
		
	}
	
	
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
