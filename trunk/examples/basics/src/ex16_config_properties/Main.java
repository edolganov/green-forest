package ex16_config_properties;

import java.io.InputStream;
import java.util.Properties;

import com.gf.core.Engine;
import com.gf.key.TraceHandlers;

import ex14_config.SomeConfigKey;
import ex15_config_default_value.OtherConfigKey;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		InputStream is = Main.class.getResourceAsStream("/ex16_config_properties/config.properties");
		
		Properties props = new Properties();
		props.load(is);
		
		Engine engine = new Engine();
		engine.setConfigValues(props);
		
		String val1 = engine.getConfig(new SomeConfigKey());
		String val2 = engine.getConfig(new OtherConfigKey());
		boolean val3 = engine.isTrueConfig(new TraceHandlers());
		System.out.println(val1);
		System.out.println(val2);
		System.out.println(val3);
		
	}

}
