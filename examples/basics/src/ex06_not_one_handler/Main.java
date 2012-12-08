package ex06_not_one_handler;

import com.gf.core.Engine;

public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		engine.putHandler(HandlerA.class);
		engine.putHandler(HandlerB.class);
		
	}

}
