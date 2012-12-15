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
package jdbc.web;

import java.sql.Connection;
import java.sql.ResultSet;

import jdbc.TestUtil;

import org.junit.Test;

import com.gf.extra.trace.Trace;
import com.gf.key.TraceHandlers;

import example.AbstractTest;
import example.app.App;
import example.common.action.GetDocsCount;
import example.storage.StorageUtil;


public class InitServletTest extends AbstractTest {
	
	
	@Test
	public void test_trace() throws Exception {
		
		InitServlet servlet = new InitServlet();
		servlet.init(TestUtil.getConfig(tmpDirPath, sessionId));
		App app = InitServlet.getApp();
		
		GetDocsCount action = new GetDocsCount();
		app.invoke(action);
		
		Trace trace = TraceHandlers.getTrace(action);
		assertNotNull(trace);
		
	}
	
	
	@Test
	public void test_init_db() throws Exception {
		
		InitServlet servlet = new InitServlet();
		servlet.init(TestUtil.getConfig(tmpDirPath, sessionId));
		
		Connection c = InitServletAccessor.getConnection(servlet);
		ResultSet rs = c.createStatement().executeQuery("select count(id) from doc");
		
		rs.next();
		assertEquals(StorageUtil.INIT_DOCS_COUNT, rs.getInt(1));
		
		c.close();
	}
	
	
	@Test
	public void test_init_app() throws Exception{
		
		InitServlet servlet = new InitServlet();
		servlet.init(TestUtil.getConfig(tmpDirPath, sessionId));
		App app = InitServlet.getApp();
		
		assertNotNull(app);
		
	}


}
