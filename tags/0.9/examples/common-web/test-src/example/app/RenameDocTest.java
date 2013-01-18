/*
 * Copyright 2012 Evgeny Dolganov (evgenij.dolganov@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
