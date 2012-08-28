package com.green.forest.api.extra.invocation;

import java.util.ArrayList;
import java.util.List;

public class TraceTree {
	
	private List<TraceElem> level = new ArrayList<TraceElem>();
	
	public TraceTree() {
		super();
	}

	public void addTraceElem(Object ob) {
		level.add(new TraceElem(ob.getClass()));
	}

	public void addSubTrace(TraceTree subTrace) {
		if(level.isEmpty()){
			throw new IllegalStateException("can't add sub tree for empty tree");
		}
		TraceElem last = level.get(level.size()-1);
		last.addSubTrace(subTrace);
	}
	
	public List<TraceElem> getLevel(){
		return new ArrayList<TraceElem>(level);
	}

}
