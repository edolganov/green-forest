package ex02_invokeUnwrap;

import com.gf.core.Engine;
import com.gf.exception.ExceptionWrapper;

import ex01_invoke.SomeAction;

public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		engine.putHandler(ActionHandlerWithException.class);
		
		//1. use unwrap
		try {
			engine.invokeUnwrap(new SomeAction());
		}catch (Exception e) {
			System.out.println(e);
		}
		
		//2. use wrap
		try {
			engine.invoke(new SomeAction());
		}catch (ExceptionWrapper e) {
			Exception myExp = e.getOriginal();
			System.out.println(myExp);
		}
		
		//3. not handle
		engine.invoke(new SomeAction()); //throws ExceptionWrapper
		
		
	}

}
