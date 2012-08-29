package com.green.forest.api.extra.invocation;

import java.util.ArrayList;
import java.util.List;

public class TraceItem {
	
	public final Class<?> type;
	
	private List<TraceTree> subTraces;

	public TraceItem(Class<?> type) {
		super();
		this.type = type;
	}



	public List<TraceTree> getSubTraces(){
		if(subTraces == null){
			return new ArrayList<TraceTree>();
		}
		return new ArrayList<TraceTree>(subTraces);
	}

	public void addSubTrace(TraceTree subTrace) {
		if(subTraces == null){
			subTraces = new ArrayList<TraceTree>();
		}
		subTraces.add(subTrace);
	}

}
