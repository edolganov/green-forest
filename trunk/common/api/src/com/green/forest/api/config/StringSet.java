package com.green.forest.api.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;

@SuppressWarnings("serial")
public class StringSet extends LinkedHashSet<String> {

	public StringSet() {
		super();
	}
	
	public StringSet(String...strings) {
		super(Arrays.asList(strings));
	}

	public StringSet(Collection<? extends String> c) {
		super(c);
	}

	public StringSet(int initialCapacity) {
		super(initialCapacity);
	}
	
	

}
