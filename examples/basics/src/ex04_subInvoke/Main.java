package ex04_subInvoke;

import com.gf.core.Engine;

import ex01_invoke.SomeAction;

public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		engine.putHandler(SomeHandler.class);
		engine.putHandler(OtherHandler.class);
		String result = engine.invoke(new SomeAction("test"));
		System.out.println(result);
		
	}

}
