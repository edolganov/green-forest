package servlet_jdbc.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import servlet_jdbc.app.App;
import servlet_jdbc.common.storage.InitStorage;
import servlet_jdbc.storage.Storage;

import com.gf.core.Engine;


public class InitServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static App app;
	
	public static App getApp(){
		if(app == null){
			throw new IllegalStateException("app is not inited");
		}
		return app;
	}
	
	/**
	 * Init the applicaton
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		app = createApp(config);
	}

	private App createApp(ServletConfig config) {
		
		//create app's engine
		Engine engine = new Engine();
		
		//init context
		Storage storage = createStorage(config);
		engine.addToContext(storage);
		
		//create app
		App app = new App(engine);	
		return app;
	}
	
	private Storage createStorage(ServletConfig config) {
		
		//create storage's engine
		Engine engine = new Engine();
		
		//create storage
		Storage storage = new Storage(engine);
		
		//init storage
		storage.invoke(new InitStorage());
		
		return storage;
	}

}
