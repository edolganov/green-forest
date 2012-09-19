package com.gf.core.context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class ContextRepository {
	
	private CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<Object>();
	
	public synchronized void add(Object ob) {
		removeExists(ob);
		list.add(0, ob);
	}
	
	private void removeExists(Object ob) {
		for (int i = list.size()-1; i>-1; i--) {
			Object candidat = list.get(i);
			if(candidat.equals(ob)){
				list.remove(i);
			}
		}
	}

	public Collection<Object> getAll() {
		ArrayList<Object> out = new ArrayList<Object>(list);
		return out;
	}

}
