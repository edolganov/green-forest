package example.app;

import org.junit.Test;

import example.common.action.GetDocsPage;
import example.common.action.RenameDoc;
import example.common.model.Doc;

public class RenameDocTest extends AbstractAppTest {
	
	
	@Test
	public void invoke_for_unexist() throws Exception{
		
		int id = -1;
		String newName = "new123";
		app.invoke(new RenameDoc(id, newName));

	}
	
	@Test
	public void invoke() throws Exception{
		
		int id = 1;
		String newName = "new123";
		app.invoke(new RenameDoc(id, newName));
		assertEquals(newName, getDocName(id));
		
		Doc resultFromDB = app.invoke(new GetDocsPage(0, 1)).list.get(0);
		assertEquals(newName, resultFromDB.name);

	}

}
