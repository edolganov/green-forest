package ex13_scan;

import actions.SomeAction;

import com.gf.core.Engine;

public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		engine.scanForAnnotations("ex13_scan");
		engine.invoke(new SomeAction());
	}

}
