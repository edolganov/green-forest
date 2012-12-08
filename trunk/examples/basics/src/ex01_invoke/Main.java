package ex01_invoke;

import actions.SomeAction;

import com.gf.core.Engine;

public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		engine.putHandler(SomeActonHandler.class);
		
		String result = engine.invoke(new SomeAction("some data"));
		System.out.println(result);
		
		SomeAction otherAction = new SomeAction("other data");
		engine.invoke(otherAction);
		String otherResult = otherAction.getOutput();
		System.out.println(otherResult);
		
		
	}

}
