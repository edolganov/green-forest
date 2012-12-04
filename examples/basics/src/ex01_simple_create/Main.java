package ex01_simple_create;

import com.gf.core.Engine;

public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		engine.putHandler(SomeActonHandler.class);
		String result = engine.invoke(new SomeAction("some data"));
		
		System.out.println(result);
		
	}

}
