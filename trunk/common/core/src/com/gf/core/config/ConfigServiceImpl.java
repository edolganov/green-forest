package com.gf.core.config;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.gf.config.ConfigKey;
import com.gf.core.config.converter.AbstractConverter;
import com.gf.core.config.converter.ConfigKeyConverters;
import com.gf.service.ConfigService;
import com.gf.util.Util;


public class ConfigServiceImpl implements ConfigService {
	
	private HashMap<Class<?>, AbstractConverter<?>> converters = new ConfigKeyConverters().map;
	private HashMap<Class<?>, Object> values = new HashMap<Class<?>, Object>();
	private ReadWriteLock rw = new ReentrantReadWriteLock();
	private Lock readLock = rw.readLock();
	private Lock writeLock = rw.writeLock();
	
	
	public <T> void addConverter(Class<T> valueType, AbstractConverter<T> converter){
		converters.put(valueType, converter);
	}
	
	
	
	@Override
	public void setConfigValues(Properties props){
		if(props == null) return;
		
		writeLock.lock();
		try {
			tryAddValues(props);
		}finally{
			writeLock.unlock();
		}
		
	} 
	
	
	@Override
	public <T> void setConfig(Class<? extends ConfigKey<T>> keyType, T value){
		Util.checkArgumentForEmpty(keyType, "type is null");
		
		writeLock.lock();
		try {
			values.put(keyType, value);
		}finally{
			writeLock.unlock();
		}
		
	}
	
	
	private void tryAddValues(Properties props) {
		
		for(Object key : props.keySet()){
			addValue((String)key, (String)props.get(key));
		}
	}


	private void addValue(String typeInfo, String rawValue) {
		
		Class<?> keyType = null;
		try {
			keyType = Class.forName(typeInfo);
		}catch (ClassNotFoundException ex) {
			//no class by this typeInfo
			return;
		}
		
		if( ! ConfigKey.class.isAssignableFrom(keyType)){
			//it's not a ConfigKey sub type
			return;
		}
		
		Class<?> valueType = null;
		try {
			valueType = (Class<?>)((ParameterizedType) keyType.getGenericSuperclass()).getActualTypeArguments()[0];
		}catch (Exception e) {
			throw new IllegalStateException("can't get value type from ["+keyType.getName()+"]", e);
		}
		
		AbstractConverter<?> converter = converters.get(valueType);
		Util.checkState( ! Util.isEmpty(converter), "can't find converter for value type ["+valueType.getName()+"]");
		
		Object value = converter.toValue(rawValue);
		values.put(keyType, value);
	}


	@Override
	public <T> T getConfig(ConfigKey<T> key) {
		Util.checkArgumentForEmpty(key, "key is null");
		
		readLock.lock();
		try {
			return tryReturnValue(key);
		}finally{
			readLock.unlock();
		}
	}
	
	@Override
	public boolean isTrueConfig(ConfigKey<Boolean> key) {
		Boolean val = getConfig(key);
		return Boolean.TRUE.equals(val);
	}

	@SuppressWarnings("unchecked")
	private <T> T tryReturnValue(ConfigKey<T> key) {
		Class<?> type = key.getClass();
		if(values.containsKey(type)){
			
			Object value = values.get(type);
			try{
				T out = (T) value;
				return out;
			}catch (Exception e) {
				throw new IllegalStateException("can't converting value to valid type for key ["+type.getName()+"]", e);
			}
			
		} else {
			if(key.hasDefaultValue()){
				try {
					return key.getDefaultValue();
				} catch (Exception e) {
					throw new IllegalStateException("can't get default value for key ["+type.getName()+"]", e);
				}
			} else {
				throw new IllegalStateException("unknown key ["+type.getName()+"]");
			}
		}
	}

}
