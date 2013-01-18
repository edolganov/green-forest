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

import java.util.List;

import org.junit.Test;

import example.common.action.GetDocsPage;
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
