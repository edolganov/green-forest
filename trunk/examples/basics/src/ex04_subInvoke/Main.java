package ex04_subInvoke;

import actions.SomeAction;

import com.gf.core.Engine;


public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		engine.putHandler(SomeHandler.class);
		engine.putHandler(OtherHandler.class);
		String result = engine.invoke(new SomeAction("test"));
		System.out.println(result);
		
	}

}
