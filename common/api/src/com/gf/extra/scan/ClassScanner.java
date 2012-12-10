package com.gf.extra.scan;

import java.util.Set;

public interface ClassScanner {
	
	Set<Class<?>> getClasses(String packageRoot, Class<?> parentClass);

}
