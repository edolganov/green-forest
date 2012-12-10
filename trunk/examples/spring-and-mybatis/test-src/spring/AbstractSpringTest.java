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
