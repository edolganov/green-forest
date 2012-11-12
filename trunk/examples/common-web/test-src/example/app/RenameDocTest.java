package example.app;

import org.junit.Test;

import example.common.app.GetDocsPage;
import example.common.app.RenameDoc;
import example.common.model.Doc;

public class RenameDocTest extends AbstractAppTest {
	
	
	@Test
	public void invoke_for_unexist() throws Exception{
		
		int id = -1;
		String newName = "new123";
		Doc unexistResult = app.invoke(new RenameDoc(id, newName));
		assertEquals(newName, unexistResult.name);

	}
	
	@Test
	public void invoke() throws Exception{
		
		int id = 1;
		String newName = "new123";
		Doc result = app.invoke(new RenameDoc(id, newName));
		assertEquals(newName, result.name);
		assertEquals(newName, getDocName(id));
		
		Doc resultFromDB = app.invoke(new GetDocsPage(0, 1)).list.get(0);
		assertEquals(newName, resultFromDB.name);

	}

}
