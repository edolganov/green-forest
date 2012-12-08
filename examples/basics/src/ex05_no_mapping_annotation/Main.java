package ex05_no_mapping_annotation;

import com.gf.core.Engine;

public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		engine.putHandler(HandlerWithNoMapping.class);
		
	}

}
