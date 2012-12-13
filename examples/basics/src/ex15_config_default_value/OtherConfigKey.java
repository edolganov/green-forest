package ex15_config_default_value;

import com.gf.config.ConfigKey;

public class OtherConfigKey extends ConfigKey<String>{

	@Override
	public boolean hasDefaultValue() {
		return false;
	}

}
