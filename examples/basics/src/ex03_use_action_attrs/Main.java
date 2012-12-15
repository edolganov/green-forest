package ex03_use_action_attrs;

import actions.SomeAction;

import com.gf.core.Engine;


public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		engine.putInterceptor(SomeInterceptor.class);
		engine.putHandler(SomeHandler.class);
		engine.invoke(new SomeAction());
		
	}

}