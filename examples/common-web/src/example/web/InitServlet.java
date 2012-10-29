package example.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


import com.gf.components.atomikos.jdbc.DataSourceManagerImpl;
import com.gf.components.atomikos.tx.TxManagerImpl;
import com.gf.components.jdbc.ConnectionInInvoke;
import com.gf.components.jdbc.DataSourceManager;
import com.gf.components.tx.TxManager;
import com.gf.components.tx.UserTransactionInInvoke;
import com.gf.core.Engine;
import com.gf.log.Log;
import com.gf.log.LogFactory;

import example.app.App;
import example.common.storage.CreateOrUpdateDataBase;
import example.storage.Storage;


public class InitServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static App app;
	
	
	public static App getApp(){
		if(app == null){
			throw new IllegalStateException("app is not inited");
		}
		return app;
	}
	
	Log log = LogFactory.getLog(getClass());
	DataSourceManager dataSourceManager;
	
	/**
	 * Init the applicaton
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		app = createApp(config);
	}

	private App createApp(ServletConfig config) throws ServletException {
		
		Storage storage = createStorage(config);
		
		//create app's engine
		Engine engine = new Engine();
		
		//init
		engine.addToContext(storage);
		engine.scanForAnnotations(App.class.getPackage());
		
		//return Application
		return new App(engine);
	}
	
	private Storage createStorage(ServletConfig config) throws ServletException {
		
		//create storage's engine
		Engine engine = new Engine();
		
		//init
		engine.addToContext(createTxManager(config));
		engine.addToContext(createDataSourceManager(config));
		
		engine.putFilter(UserTransactionInInvoke.class);
		engine.putFilter(ConnectionInInvoke.class);
		
		engine.scanForAnnotations(Storage.class.getPackage());
		
		//invoke actions
		engine.invoke(new CreateOrUpdateDataBase());
		
		//return Storage
		return new Storage(engine);
	}
	
	

	private DataSourceManager createDataSourceManager(ServletConfig config) throws ServletException {
		try {
			DataSourceManagerImpl manager = new DataSourceManagerImpl();
			manager.setUser(config.getInitParameter("db-user"));
			manager.setPassword(config.getInitParameter("db-password"));
			manager.setDriverClass(config.getInitParameter("db-driver"));
			manager.setUrl(config.getInitParameter("db-url"));
			manager.setPoolSize(Integer.parseInt(config.getInitParameter("db-pool-size")));
			manager.init();
			
			this.dataSourceManager = manager;
			
			return manager;
		}catch (Exception e) {
			throw new ServletException("can't init datasource manager", e);
		}

	}

	private TxManager createTxManager(ServletConfig config) throws ServletException {

		try {
			TxManagerImpl manager = new TxManagerImpl();
			manager.init();
			return manager;
		} catch (Exception e) {
			throw new ServletException("can't init tx manager", e);
		}
		
	}

}
