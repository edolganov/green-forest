package com.gf.core.config.converter;

import java.io.File;
import java.util.HashMap;

import com.gf.config.IntegerPair;
import com.gf.config.StringList;
import com.gf.config.StringSet;


public class ConfigKeyConverters {
	
	public HashMap<Class<?>, AbstractConverter<?>> map = new HashMap<Class<?>, AbstractConverter<?>>();
	
	{
		map.put(String.class, new StringConverter());
		map.put(Integer.class, new IntConverter());
		map.put(Long.class, new LongConverter());
		map.put(Boolean.class, new BooleanConverter());
		map.put(Double.class, new DoubleConverter());
		map.put(File.class, new FileConverter());
		map.put(StringList.class, new StringListConverter());
		map.put(StringSet.class, new StringSetConverter());
		map.put(IntegerPair.class, new IntegerPairConverter());
		map.put(Class.class, new ClassConverter());
		
	}

}
