package servlet_jdbc.storage;

import java.sql.Connection;
import org.junit.Test;

import servlet_jdbc.common.app.RenameDoc;
import servlet_jdbc.common.model.Doc;

import com.gf.core.Engine;

public class RenameDocTest extends AbstractStorageTest {
	
	
	@Test
	public void invoke_for_unexist() throws Exception{
		
		Connection c = getConnection();
		initDatabase(c);
		
		Engine engine = new Engine();
		engine.putHandler(RenameDocHandler.class);
		engine.addToContext(c);
		
		int id = -1;
		String newName = "new123";
		Doc unexistResult = engine.invoke(new RenameDoc(id, newName));
		assertEquals(newName, unexistResult.name);

	}
	
	@Test
	public void invoke() throws Exception{
		
		Connection c = getConnection();
		initDatabase(c);
		
		Engine engine = new Engine();
		engine.putHandler(RenameDocHandler.class);
		engine.addToContext(c);
		
		int id = 1;
		String newName = "new123";
		Doc result = engine.invoke(new RenameDoc(id, newName));
		assertEquals(newName, result.name);
		assertEquals(newName, getDocName(c, id));

	}

}
