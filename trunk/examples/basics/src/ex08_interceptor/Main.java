package ex08_interceptor;

import actions.SomeAction;

import com.gf.core.Engine;

public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		engine.putHandler(SomeHandler.class);
		engine.putInterceptor(SomeInterceptor.class);
		String result = engine.invoke(new SomeAction("test"));
		
		System.out.println(result);
	}

}
