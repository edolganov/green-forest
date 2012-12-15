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
package jdbc.storage.handler;

import javax.sql.DataSource;

import jdbc.TestUtil;
import jdbc.web.InitServlet;
import jdbc.web.InitServletAccessor;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import example.app.AbstractAppTest;
import example.app.AppContext;
import example.app.AppProvider;
import example.app.CreateDataBaseTest;
import example.app.GetDocsCountTest;
import example.app.GetDocsPageTest;
import example.app.App;
import example.app.RenameDocTest;
import example.app.TxTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	CreateDataBaseTest.class,
	GetDocsCountTest.class,
	GetDocsPageTest.class,
	RenameDocTest.class,
	TxTest.class
})
public class AppTests_TestCase {
	
	@BeforeClass
	public static void initProvider(){
		AbstractAppTest.setTestContext(new AppProvider() {
			
			@Override
			public AppContext createContext() throws Exception {
				
				String sessionId = String.valueOf(System.currentTimeMillis());
				String tmpDirPath = "./tmp";
				
				InitServlet servlet = new InitServlet();
				servlet.init(TestUtil.getConfig(tmpDirPath, sessionId));
				
				App app = InitServlet.getApp();
				DataSource ds = InitServletAccessor.getDataSource(servlet);
				
				return new AppContext(app, ds);
			}
		});
	}
	
	@AfterClass
	public static void destroyProvider(){
		AbstractAppTest.setTestContext(null);
	}

}
