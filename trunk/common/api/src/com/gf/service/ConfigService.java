package com.gf.service;

import java.util.Properties;

import com.gf.config.ConfigKey;

public interface ConfigService {
	
	<T> T getConfig(ConfigKey<T> key);
	
	<T> void setConfig(Class<? extends ConfigKey<T>> keyType, T value);
	
	void setConfigValues(Properties props);
	
	boolean isTrueConfig(ConfigKey<Boolean> key);

}
