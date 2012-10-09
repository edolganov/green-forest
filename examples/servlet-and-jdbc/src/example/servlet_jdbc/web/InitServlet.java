package example.servlet_jdbc.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.gf.core.Engine;

import example.servlet_jdbc.app.App;
import example.servlet_jdbc.storage.Storage;

public class InitServlet extends HttpServlet {
	
	private static App app;
	
	public static App getApp(){
		if(app == null){
			throw new IllegalStateException("app is not inited");
		}
		return app;
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		app = createApp(config);
	}

	private App createApp(ServletConfig config) {
		
		Engine engine = new Engine();
		
		Storage storage = createStorage(config);
		engine.addToContext(storage);
		
		App app = new App(engine);
		return app;
	}
	
	private Storage createStorage(ServletConfig config) {
		
		Engine engine = new Engine();
		
		Storage storage = new Storage(engine);
		return storage;
	}

}
