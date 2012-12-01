package com.gf.core.scan;

import java.util.Set;

public interface ClassScan {
	
	Set<Class<?>> getClasses(String packageRoot, Class<?> parentClass);

}
