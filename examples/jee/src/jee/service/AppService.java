package jee.service;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import com.gf.Action;
import com.gf.core.Engine;
import com.gf.exception.ExceptionWrapper;
import com.gf.exception.invoke.InvocationException;
import com.gf.service.ActionService;

@Stateless
public class AppService implements ActionService {
	
	private static int instanceCount;
	private static Engine engine;
	
	private static synchronized Engine getEngineAndRegisterNewInstance(){
		if(engine == null){
			engine = createEngine();
		}
		instanceCount++;
		return engine;
	}
	
	private static synchronized void unregisterInstance(){
		instanceCount--;
		if(instanceCount < 1){
			engine = null;
		}
	}
	
	private static Engine createEngine() {
		return null;
	}
	

	@PostConstruct
	public void init(){
		
	}

	@Override
	public <I, O> O invoke(Action<I, O> action) throws InvocationException,
			ExceptionWrapper, RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I, O> O invokeUnwrap(Action<I, O> action)
			throws InvocationException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
