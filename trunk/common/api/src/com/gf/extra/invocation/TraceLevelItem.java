package com.gf.extra.invocation;

import java.util.ArrayList;
import java.util.List;

public class TraceLevelItem extends TraceElement {
	
	public final Object owner;
	
	private List<TraceElement> subElements;

	public TraceLevelItem(Object owner) {
		super();
		this.owner = owner;
	}



	@Override
	public List<TraceElement> getChildren(){
		if(subElements == null){
			return new ArrayList<TraceElement>();
		}
		return new ArrayList<TraceElement>(subElements);
	}

	public void addSubElement(TraceElement subElement) {
		if(subElements == null){
			subElements = new ArrayList<TraceElement>();
		}
		subElements.add(subElement);
	}


	@Override
	public String toStringCurObject() {
		return getClass().getSimpleName()+" [owner="+owner+", childrenCount="+(subElements == null? 0 : subElements.size())+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((subElements == null) ? 0 : subElements.hashCode());
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
		if (subElements == null) {
			if (other.subElements != null)
				return false;
		} else if (!subElements.equals(other.subElements))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}

}
