package ex09_interceptor_order;

import actions.SomeAction;

import com.gf.core.Engine;

import ex01_invoke.SomeActonHandler;

public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		engine.putInterceptor(InterceptorA.class);
		engine.putInterceptor(InterceptorB.class);
		engine.putInterceptor(InterceptorC.class);
		engine.putHandler(SomeActonHandler.class);
		engine.invoke(new SomeAction());
	}

}
