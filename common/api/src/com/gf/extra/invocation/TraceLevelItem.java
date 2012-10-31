package com.gf.extra.invocation;

import static com.gf.extra.invocation.TraceLevel.appendTabs;
import static com.gf.extra.invocation.TraceLevel.toStringLevelIndex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TraceLevelItem {
	
	public final Class<?> type;
	
	private List<TraceLevel> subLists;

	public TraceLevelItem(Class<?> type) {
		super();
		this.type = type;
	}



	public List<TraceLevel> getSubLists(){
		if(subLists == null){
			return new ArrayList<TraceLevel>();
		}
		return new ArrayList<TraceLevel>(subLists);
	}

	public void addSubList(TraceLevel subTrace) {
		if(subLists == null){
			subLists = new ArrayList<TraceLevel>();
		}
		subLists.add(subTrace);
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((subLists == null) ? 0 : subLists.hashCode());
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
		if (subLists == null) {
			if (other.subLists != null)
				return false;
		} else if (!subLists.equals(other.subLists))
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

		
		List<TraceLevel> items = Collections.emptyList();
		if(subLists != null){
			items = subLists;
		}
		
		Iterator<TraceLevel> i = items.iterator();
		if ( i.hasNext()){
			
			sb.append('\n');
			
			while(i.hasNext()) {
				
				toStringLevelIndex.set(levelIndex+1);
				TraceLevel e = i.next();
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
