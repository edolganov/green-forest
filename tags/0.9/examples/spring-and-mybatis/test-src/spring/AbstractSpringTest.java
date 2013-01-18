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
package spring;

import javax.sql.DataSource;

import org.junit.Before;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import example.AbstractTest;
import example.app.App;
import example.common.action.CreateDataBase;

public abstract class AbstractSpringTest extends AbstractTest {
	
	protected App app;
	protected DataSource ds;
	
	@Before
	public void initApp(){
		
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		BeanFactory factory = (BeanFactory) appContext;
		
		app = (App) factory.getBean("app");
		app.invoke(new CreateDataBase());
		
		ds = (DataSource)factory.getBean("dataSource");
		
	}

}
