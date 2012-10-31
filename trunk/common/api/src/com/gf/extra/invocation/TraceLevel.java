package com.gf.extra.invocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.gf.util.Util;

public class TraceLevel implements TraceElement {
	
	static final ThreadLocal<Integer> toStringLevelIndex = new ThreadLocal<Integer>();
	
	private List<TraceLevelItem> items = new ArrayList<TraceLevelItem>();
	
	public TraceLevel() {
		super();
	}

	public TraceLevel(Object... traceTree) {
		this();
		buildTree(traceTree);
	}

	private void buildTree(Object[] traceTree) {
		if(Util.isEmpty(traceTree)){
			return;
		}
		for(Object ob : traceTree){
			addItem(this, ob);
		}
	}
	
	private void addItem(TraceLevel trace, Object ob){
		//item
		if(ob instanceof Class){
			trace.createAndAddItem(ob);
		}
		//item's sub trace
		else if(ob instanceof List<?>){
			
			TraceLevel subTrace = new TraceLevel();
			trace.addSubListToLastItem(subTrace);
			
			List<?> subItems = (List<?>)ob;
			for(int i=0; i < subItems.size(); ++i){
				Object subItem = subItems.get(i);
				addItem(subTrace, subItem);
			}
		}
		//unknown
		else {
			throw new IllegalArgumentException("invalid type of elem: "+ob);
		}
	}

	public void createAndAddItem(Object ob) {
		Class<?> clazz = null;
		if(ob instanceof Class){
			clazz = (Class<?>)ob;
		}else {
			clazz = ob.getClass();
		}
		TraceLevelItem item = new TraceLevelItem(clazz);
		items.add(item);
	}

	public void addSubListToLastItem(TraceLevel subTrace) {
		if(items.isEmpty()){
			throw new IllegalStateException("can't add sub tree for empty tree");
		}
		TraceLevelItem last = items.get(items.size()-1);
		last.addSubElement(subTrace);
	}
	
	public TraceLevelItem getItem(int index){
		return items.get(index);
	}
	
	public TraceLevelItem getLastItem(){
		if(items.size() == 0){
			return null;
		}
		return items.get(items.size()-1);
	}
	
	public int getSize(){
		return items.size();
	}
	
	public List<TraceLevelItem> getItems(){
		return new ArrayList<TraceLevelItem>(items);
	}
	
	@Override
	public List<TraceElement> getChildren() {
		ArrayList<TraceElement> out = new ArrayList<TraceElement>();
		for (TraceLevelItem item : items) {
			out.add(item);
		}
		return out;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
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
		TraceLevel other = (TraceLevel) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
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
		sb.append("[TraceLevel:");
		
		List<TraceLevelItem> items = Collections.emptyList();
		if(this.items != null){
			items = this.items;
		}
		
        Iterator<TraceLevelItem> i = items.iterator();
		if (! i.hasNext()){
		    sb.append(" empty");
		} else {
			
			sb.append('\n');
			
			while(i.hasNext()) {
				
				toStringLevelIndex.set(levelIndex+1);
				TraceLevelItem e = i.next();
			    sb.append(e);
			    toStringLevelIndex.set(levelIndex);
			    
			    if ( i.hasNext()){
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
	
	static void appendTabs(StringBuilder sb, int levelIndex){
		for(int i=0; i < levelIndex; ++i){
			sb.append('\t');
		}
	}
	


}
