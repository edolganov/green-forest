package ex03_use_action_attrs;

import com.gf.core.Engine;

import ex01_invoke.SomeAction;

public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		engine.putInterceptor(SomeInterceptor.class);
		engine.putHandler(SomeHandler.class);
		engine.invoke(new SomeAction());
		
	}

}
