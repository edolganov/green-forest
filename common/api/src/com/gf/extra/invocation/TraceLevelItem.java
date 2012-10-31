package com.gf.extra.invocation;

import java.util.ArrayList;
import java.util.List;

public class TraceLevelItem extends TraceElement {
	
	public final Class<?> type;
	
	private List<TraceElement> subElements;

	public TraceLevelItem(Class<?> type) {
		super();
		this.type = type;
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
		return getClass().getSimpleName()+" [owner="+type+", childrenCount="+(subElements == null? 0 : subElements.size())+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((subElements == null) ? 0 : subElements.hashCode());
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
		TraceLevelItem other = (TraceLevelItem) obj;
		if (subElements == null) {
			if (other.subElements != null)
				return false;
		} else if (!subElements.equals(other.subElements))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
