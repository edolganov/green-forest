package ex12_inject;

import actions.SomeAction;

import com.gf.core.Engine;

public class Main {
	
	public static void main(String[] args) {
		
		SomeService someService = new SomeService();
		Engine engine = new Engine();
		engine.addToContext(someService);
		engine.putHandler(SomeHandler.class);
		engine.invoke(new SomeAction());
	}

}
