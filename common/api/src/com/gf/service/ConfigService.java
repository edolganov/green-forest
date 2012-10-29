package com.gf.service;

import com.gf.config.ConfigKey;

public interface ConfigService {
	
	<T> T getConfig(ConfigKey<T> key);
	
	<T> void setConfig(Class<? extends ConfigKey<T>> keyType, T value);
	
	boolean isTrueConfig(ConfigKey<Boolean> key);

}
