package com.gf.core.context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContextRepository {
	
	private CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<Object>();
	
	public synchronized void add(Object ob) {
		list.add(0, ob);
	}

	public Collection<Object> getAll() {
		ArrayList<Object> out = new ArrayList<Object>(list);
		return out;
	}

}
