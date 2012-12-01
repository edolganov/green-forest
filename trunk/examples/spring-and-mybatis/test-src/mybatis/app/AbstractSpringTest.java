package mybatis.app;

import javax.sql.DataSource;

import org.junit.Before;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import example.AbstractTest;
import example.app.IApp;

public abstract class AbstractSpringTest extends AbstractTest {
	
	protected IApp app;
	protected DataSource ds;
	
	@Before
	public void initApp(){
		
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		BeanFactory factory = (BeanFactory) appContext;
		
		app = (IApp) factory.getBean("app");
		ds = (DataSource)factory.getBean("dataSource");
		
	}

}
