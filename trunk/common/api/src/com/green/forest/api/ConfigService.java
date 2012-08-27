package com.green.forest.api;

import com.green.forest.api.config.ConfigKey;

public interface ConfigService {
	
	<T> T getValue(ConfigKey<T> key);
	
	<T> void addValue(Class<? extends ConfigKey<T>> keyType, T value);
	
	
	boolean isTrue(ConfigKey<Boolean> key);

}
