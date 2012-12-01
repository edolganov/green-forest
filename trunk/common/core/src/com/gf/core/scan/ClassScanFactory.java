package com.gf.core.scan;

import com.gf.log.Log;
import com.gf.log.LogFactory;

public class ClassScanFactory {
	
	private static Log log = LogFactory.getLog(ClassScanFactory.class);
	private static ClassScan instance;
	
	public static ClassScan getScan(){
		
		if(instance == null){
			instance = createInstance();
		}
		return instance;
		

	}

	private static ClassScan createInstance() {
		
		//reflectionsScan
		String reflectionsClass = "com.gf.components.reflections.ReflectionsScan";
		try {
			Class<?> clazz = Class.forName(reflectionsClass);
			ClassScan reflectionsScan = (ClassScan)clazz.newInstance();
			return reflectionsScan;
		}catch (ClassNotFoundException e) {
			//this module not found
		}catch (Exception e) {
			log.error("can't create instance of "+reflectionsClass, e);
		}
		
		//default
		ClassScan defaultScan = new DefaultClassScan();
		return defaultScan;
	}

}
