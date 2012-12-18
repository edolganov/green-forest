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
package mybatis.app;

import javax.sql.DataSource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import example.app.AbstractAppTest;
import example.app.App;
import example.app.AppContext;
import example.app.AppProvider;
import example.app.CreateDataBaseTest;
import example.app.GetDocsCountTest;
import example.app.GetDocsPageTest;
import example.app.RenameDocTest;
import example.app.TxTest;
import example.common.action.CreateDataBase;

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
			
			public AppContext createContext() throws Exception {
				
				ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
				BeanFactory factory = (BeanFactory) appContext;
				
				App app = (App) factory.getBean("app");
				DataSource ds = (DataSource)factory.getBean("dataSource");
				
				app.invoke(new CreateDataBase());
				
				return new AppContext(app, ds);
			}
		});
	}
	
	@AfterClass
	public static void destroyProvider(){
		AbstractAppTest.setTestContext(null);
	}

}
