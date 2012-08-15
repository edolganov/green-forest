package com.green.forest.core.config;

import com.green.forest.api.config.ConfigKey;

public abstract class ConfigService {
	
	public abstract <T> T getValue(ConfigKey<T> key);
	
	public boolean isTrue(ConfigKey<Boolean> key){
		Boolean val = getValue(key);
		return Boolean.TRUE.equals(val);
	}

}
