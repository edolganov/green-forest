package ex10_filter;

import actions.SomeAction;

import com.gf.core.Engine;

public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		engine.putHandler(SomeHandler.class);
		engine.putFilter(SomeFilter.class);
		engine.invoke(new SomeAction());
	}

}
