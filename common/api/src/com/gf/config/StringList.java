package com.gf.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings("serial")
public class StringList extends ArrayList<String> {

	public StringList() {
		super();
	}
	
	public StringList(String...strings) {
		super(Arrays.asList(strings));
	}

	public StringList(Collection<? extends String> c) {
		super(c);
	}

	public StringList(int initialCapacity) {
		super(initialCapacity);
	}
	
	

}
