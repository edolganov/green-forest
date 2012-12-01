package jee.app;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.gf.components.jee.ActionServiceBean;
import com.gf.core.Engine;
import com.gf.key.core.TraceHandlers;
import com.gf.service.ActionService;

import example.app.App;
import example.common.action.CreateDataBase;
import example.storage.Storage;

@Stateless(name="App")
@Local(App.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class AppBean extends ActionServiceBean implements App {
	
	@EJB(name="Storage")
	Storage storage;
	
	
	@Override
	public ActionService createActionService() {
		
		//create app's engine
		Engine engine = new Engine("App Engine");
		
		//init
		engine.addToContext(storage);
		engine.scanPackageForAnnotations(App.class);
		engine.setConfig(TraceHandlers.class, true);
		
		//invoke actions
		engine.invoke(new CreateDataBase());

		return engine;
	}



}
