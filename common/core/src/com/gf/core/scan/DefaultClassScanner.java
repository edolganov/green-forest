package com.gf.core.scan;

import java.util.Set;

import com.gf.core.util.ScanUtil;
import com.gf.extra.scan.ClassScanner;

@SuppressWarnings({"rawtypes", "unchecked"})
public class DefaultClassScanner implements ClassScanner {

	
	@Override
	public Set<Class<?>> getClasses(String packageRoot, Class<?> parentClass) {
		
		ScanUtil<Class> scanUtil = new ScanUtil<Class>();
	    scanUtil.find(new ScanUtil.IsA(parentClass), packageRoot);
	    Set set = scanUtil.getClasses();
	    
		return set;
	}
	
	

}
