package ex07_many_actions_for_one_handler;

import actions.OtherAction;
import actions.SomeAction;

import com.gf.core.Engine;

public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		engine.putHandler(CommonHandler.class);
		engine.invoke(new SomeAction());
		engine.invoke(new OtherAction());
	}

}
