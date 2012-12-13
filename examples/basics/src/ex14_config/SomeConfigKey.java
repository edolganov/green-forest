package ex14_config;

import com.gf.config.ConfigKey;

public class SomeConfigKey extends ConfigKey<String>{

	@Override
	public boolean hasDefaultValue() {
		return true;
	}
	
	@Override
	public String getDefaultValue() throws Exception {
		return "some default value";
	}

}
