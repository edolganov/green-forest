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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((subTraces == null) ? 0 : subTraces.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TraceItem other = (TraceItem) obj;
		if (subTraces == null) {
			if (other.subTraces != null)
				return false;
		} else if (!subTraces.equals(other.subTraces))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	

}
