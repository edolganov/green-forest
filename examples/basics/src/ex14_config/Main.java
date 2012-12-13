package ex14_config;

import actions.SomeAction;

import com.gf.core.Engine;

public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		engine.putHandler(SomeHandler.class);
		engine.setConfig(SomeConfigKey.class, "some value");
		engine.invoke(new SomeAction());
	}

}
