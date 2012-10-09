package example.servlet_jdbc.model;

import com.gf.core.Engine;
import com.gf.service.ActionService;


public class Application {
	
	/** single instance of application */
	private static Application instance = null;
	
	private Engine appEngine;
	
	private Application(){
		
		ActionService storageEngine = createStorageService();
		
		appEngine = new Engine();
		appEngine.addToContext(storageEngine);
	}
	
	
	private ActionService createStorageService() {
		Engine storageEngine = new Engine();
		return storageEngine;
	}



	/**
	 * Init the Application
	 */
	public static void init(){
		if(instance == null){
			instance = new Application();
		}
	}
	
	/**
	 * Get the Application if it inited
	 * @return the application
	 */
	public static Application get(){
		if(instance == null){
			throw new IllegalStateException("app is not inited");
		}
		return instance;
	}

}
