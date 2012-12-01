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

import example.app.IApp;
import example.common.action.CreateDataBase;
import example.storage.IStorage;

@Stateless(name="App")
@Local(ActionService.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class App extends ActionServiceBean implements IApp {
	
	@EJB(name="Storage")
	IStorage storage;
	
	
	@Override
	public ActionService createActionService() {
		
		//create app's engine
		Engine engine = new Engine("App Engine");
		
		//init
		engine.addToContext(storage);
		engine.scanForAnnotations("example.app.handler");
		engine.setConfig(TraceHandlers.class, true);
		
		//invoke actions
		engine.invoke(new CreateDataBase());

		return engine;
	}



}
