package com.gf.key.scan;

import com.gf.config.ConfigKey;
import com.gf.extra.scan.ClassScanner;

public class ClassScannerKey extends ConfigKey<Class<?>>{

	@Override
	public boolean hasDefaultValue() {
		return true;
	}
	
	@Override
	public Class<?> getDefaultValue() throws Exception {
		
		//ReflectionsScanner
		try {
			return Class.forName("com.gf.components.reflections.ReflectionsScanner");
		}catch (ClassNotFoundException e) {
			//nothing
		}
		
		//DefaultClassScanner
		try {
			return Class.forName("com.gf.core.scan.DefaultClassScanner");
		}catch (Exception e) {
			//nothing
		}
		
		throw new IllegalStateException("can't find any default implementation of "+ClassScanner.class);
		
		
	}

}
