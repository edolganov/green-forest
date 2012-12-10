package com.gf.core.engine.scan;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.gf.extra.scan.ClassScanner;

public class TestClassScanner implements ClassScanner {
	
	public static final AtomicInteger instanceCounter = new AtomicInteger();
	
	public TestClassScanner() {
		instanceCounter.incrementAndGet();
	}

	@Override
	public Set<Class<?>> getClasses(String packageRoot, Class<?> parentClass) {
		return Collections.emptySet();
	}

}
