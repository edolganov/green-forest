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

import java.sql.Connection;

import jdbc.storage.handler.GetDocsCountHandler;
import jdbc.storage.handler.GetDocsPageHandler;

import org.junit.Test;

import test.model.ThrowExceptionAfterHandler;

import com.gf.core.Engine;
import com.gf.exception.TestRuntimeException;

import example.common.action.GetDocsPage;
import example.common.action.RenameDoc;
import example.common.model.Doc;

public class TxTest extends AbstractAppTest {
	
	@Test
	public void test_rollback() throws Exception{
		
		Doc initDoc = app.invoke(new GetDocsPage(0, 1)).list.get(0);
		
		appEngine.putInterceptor(ThrowExceptionAfterHandler.class);
		
		int id = 1;
		String newName = "new123";
		try {
			app.invoke(new RenameDoc(id, newName));
			fail_exception_expected();
		}catch (TestRuntimeException e) {
			//ok
		}
		
		Connection c = ds.getConnection();
		
		Engine engine2 = new Engine();
		engine2.putHandler(GetDocsPageHandler.class);
		engine2.putHandler(GetDocsCountHandler.class);
		engine2.addToContext(c);
		Doc result = engine2.invoke(new GetDocsPage(0, 1)).list.get(0);
		assertEquals(initDoc.name, result.name);
		
	}

}
