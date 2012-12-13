package ex15_config_default_value;

import com.gf.core.Engine;

import ex14_config.SomeConfigKey;

public class Main {
	
	public static void main(String[] args) {
		
		Engine engine = new Engine();
		
		String val1 = engine.getConfig(new SomeConfigKey());
		System.out.println(val1);
		
		engine.setConfig(SomeConfigKey.class, "some value");
		String val2 = engine.getConfig(new SomeConfigKey());
		System.out.println(val2);
		
		engine.getConfig(new OtherConfigKey());
		
	}

}
