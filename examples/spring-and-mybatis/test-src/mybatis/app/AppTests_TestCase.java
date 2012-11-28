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
			
			@Override
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
