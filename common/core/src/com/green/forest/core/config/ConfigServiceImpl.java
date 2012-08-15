package com.green.forest.core.config;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.green.forest.api.config.ConfigKey;
import com.green.forest.core.config.converter.AbstractConverter;
import com.green.forest.core.config.converter.ConfigKeyConverters;
import com.green.forest.util.Util;


public class ConfigServiceImpl extends ConfigService {
	
	private static final String PROPS_CONFIG_PREFFIX = "config.";
	
	private HashMap<Class<?>, AbstractConverter<?>> converters = new ConfigKeyConverters().map;
	private HashMap<Class<?>, Object> values = new HashMap<Class<?>, Object>();
	private ReadWriteLock rw = new ReentrantReadWriteLock();
	private Lock readLock = rw.readLock();
	private Lock writeLock = rw.writeLock();
	
	
	public <T> void addConverter(Class<T> valueType, AbstractConverter<T> converter){
		converters.put(valueType, converter);
	}
	
	
	
	public void addValues(Properties props){
		Util.checkArgumentForEmpty(props, "props is null");
		
		writeLock.lock();
		try {
			tryAddValues(props);
		}finally{
			writeLock.unlock();
		}
		
	} 
	
	
	public <T> void addValue(Class<? extends ConfigKey<T>> keyType, T value){
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
			if(((String)key).startsWith(PROPS_CONFIG_PREFFIX)){
				addValue((String)key, (String)props.get(key));
			}
		}
		
	}


	private void addValue(String typeInfo, String rawValue) {
		String keyTypeStr = typeInfo.substring(PROPS_CONFIG_PREFFIX.length());
		
		Class<?> keyType = null;
		try {
			keyType = Class.forName("com.mindstorage.api.config."+keyTypeStr);
		}catch (ClassNotFoundException e) {
			try {
				keyType = Class.forName(keyTypeStr);
			}catch (ClassNotFoundException ex) {}
		}
		
		Util.checkArgumentForEmpty(keyType, "can't find type by key: "+typeInfo);
		
		Class<?> valueType = null;
		try {
			valueType = (Class<?>)((ParameterizedType) keyType.getGenericSuperclass()).getActualTypeArguments()[0];
		}catch (Exception e) {
			throw new IllegalStateException("can't get value type from "+keyType.getName(), e);
		}
		
		AbstractConverter<?> converter = converters.get(valueType);
		Util.checkState( ! Util.isEmpty(converter), "can't find converter for value type: "+valueType.getName());
		
		Object value = converter.toValue(rawValue);
		values.put(keyType, value);
	}


	@Override
	public <T> T getValue(ConfigKey<T> key) {
		Util.checkArgumentForEmpty(key, "key is null");
		
		readLock.lock();
		try {
			return tryReturnValue(key);
		}finally{
			readLock.unlock();
		}
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
				throw new IllegalStateException("can't converting value to valid type for key"+type.getName(), e);
			}
			
		} else {
			if(key.hasDefaultValue()){
				return key.getDefaultValue();
			} else {
				throw new IllegalStateException("unknown key "+type.getName());
			}
		}
	}

}
