package com.gf.core.scan;

import java.util.Set;

import com.gf.core.util.ScanUtil;

@SuppressWarnings({"rawtypes", "unchecked"})
public class DefaultClassScan implements ClassScan {

	
	@Override
	public Set<Class<?>> getClasses(String packageRoot, Class<?> parentClass) {
		
		ScanUtil<Class> scanUtil = new ScanUtil<Class>();
	    scanUtil.find(new ScanUtil.IsA(parentClass), packageRoot);
	    Set set = scanUtil.getClasses();
	    
		return set;
	}
	
	

}
