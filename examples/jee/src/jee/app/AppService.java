package jee.app;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gf.Action;
import com.gf.common.ActionServiceFactory;
import com.gf.common.ActionServiceSingleton;
import com.gf.components.jee.SimpleTransactionManager;
import com.gf.core.Engine;
import com.gf.exception.ExceptionWrapper;
import com.gf.exception.invoke.InvocationException;
import com.gf.service.ActionService;

import example.app.handler.CheckDocNameHandler;

@Stateless(name="AppService")
@Local(ActionService.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class AppService implements ActionService, ActionServiceFactory {
	
	private static final ActionServiceSingleton serviceSingleton = new ActionServiceSingleton();
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Resource
	SessionContext sessionContext;
	
	ActionService actionService;
	
	
	@PostConstruct
	public void init(){
		actionService = serviceSingleton.getServiceAndRegisterClient(this);
	}
	
	@PreDestroy
	public void preDestroy(){
		serviceSingleton.unregisterClient();
	}
	
	@Override
	public ActionService createActionService() {
		
		Engine engine = new Engine();
		engine.addToContext(entityManager);
		engine.addToContext(sessionContext);
		engine.putFilter(SimpleTransactionManager.class);
		engine.scanForAnnotations(getClass().getPackage());
		engine.putHandler(CheckDocNameHandler.class);
		
		return engine;
	}

	@Override
	public <I, O> O invoke(Action<I, O> action) throws InvocationException, ExceptionWrapper, RuntimeException {
		return (O) actionService.invoke(action);
	}

	@Override
	public <I, O> O invokeUnwrap(Action<I, O> action) throws InvocationException, Exception {
		return (O) actionService.invokeUnwrap(action);
	}



}
