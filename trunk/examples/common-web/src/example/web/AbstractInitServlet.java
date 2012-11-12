package example.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.gf.core.Engine;
import com.gf.key.core.TraceHandlers;
import com.gf.log.Log;
import com.gf.log.LogFactory;

import example.app.App;
import example.common.app.CreateDataBase;
import example.storage.Storage;


public abstract class AbstractInitServlet extends HttpServlet {
	
	private static App app;
	
	
	public static App getApp(){
		if(app == null){
			throw new IllegalStateException("app is not inited");
		}
		return app;
	}
	
	Log log = LogFactory.getLog(getClass());
	
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
		Engine engine = new Engine("App Engine");
		
		//init
		engine.addToContext(storage);
		engine.scanForAnnotations(App.class.getPackage());
		engine.setConfig(TraceHandlers.class, true);
		
		//invoke actions
		engine.invoke(new CreateDataBase());
		
		//return Application
		return new App(engine);
	}
	
	protected abstract Storage createStorage(ServletConfig config) throws ServletException;

}
