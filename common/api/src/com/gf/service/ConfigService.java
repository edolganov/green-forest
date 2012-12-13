package com.gf.service;

import java.util.Properties;

import com.gf.config.ConfigKey;
import com.gf.exception.config.EmptyClassException;
import com.gf.exception.config.GetConfigValueException;
import com.gf.exception.config.ParsePropertiesException;

public interface ConfigService {
	
	<T> void setConfig(Class<? extends ConfigKey<T>> keyType, T value) throws EmptyClassException;
	
	void setConfigValues(Properties props) throws ParsePropertiesException;

	<T> T getConfig(ConfigKey<T> key) throws GetConfigValueException;

	boolean isTrueConfig(ConfigKey<Boolean> key) throws GetConfigValueException;

}
