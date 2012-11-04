package com.gf.extra.invocation;

import java.util.ArrayList;
import java.util.List;

public class TraceLevelItem extends TraceElement {
	
	public final Object owner;
	
	private List<TraceLevel> children;

	public TraceLevelItem(Object owner) {
		super();
		this.owner = owner;
	}



	@Override
	public List<TraceElement> getChildren(){
		if(children == null){
			return new ArrayList<TraceElement>();
		}
		return new ArrayList<TraceElement>(children);
	}

	@Override
	public void addChild(TraceElement child) {
		if(child instanceof TraceLevel){
			if(children == null){
				children = new ArrayList<TraceLevel>();
			}
			children.add((TraceLevel)child);
		} else {
			throw new IllegalStateException("expected type: "+TraceElement.class+" but was: "+child);
		}

	}


	@Override
	public String toStringCurObject() {
		return getClass().getSimpleName()+" [owner="+owner+", childrenCount="+(children == null? 0 : children.size())+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		TraceLevelItem other = (TraceLevelItem) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}

}
