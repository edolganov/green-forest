package com.gf.extra.invocation;

import static com.gf.extra.invocation.TraceLevel.appendTabs;
import static com.gf.extra.invocation.TraceLevel.toStringLevelIndex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TraceLevelItem implements TraceElement {
	
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

	public void addSubElement(TraceLevel subTrace) {
		if(subElements == null){
			subElements = new ArrayList<TraceElement>();
		}
		subElements.add(subTrace);
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



	@Override
	public String toString() {
		
		boolean rootCall = false;
		Integer levelIndex = toStringLevelIndex.get();
		if(levelIndex == null){
			rootCall = true;
			levelIndex = 0;
			toStringLevelIndex.set(levelIndex);
		}
		
		StringBuilder sb = new StringBuilder();
		appendTabs(sb, levelIndex);
		sb.append("[TraceLevelItem: ");
		sb.append(type == null? "null" : type.getName());

		
		List<TraceElement> items = Collections.emptyList();
		if(subElements != null){
			items = subElements;
		}
		
		Iterator<TraceElement> i = items.iterator();
		if ( i.hasNext()){
			
			sb.append('\n');
			
			while(i.hasNext()) {
				
				toStringLevelIndex.set(levelIndex+1);
				TraceElement e = i.next();
			    sb.append(e);
			    toStringLevelIndex.set(levelIndex);
			    
			    if (i.hasNext()){
			    	sb.append('\n');
			    }
			}
		}
		
    	sb.append(']');
		
		if(rootCall){
			toStringLevelIndex.remove();
		}
		
		return sb.toString();
	}




	
	

}
