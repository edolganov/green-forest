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
import example.app.IApp;
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
				
				IApp app = InitServlet.getApp();
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
