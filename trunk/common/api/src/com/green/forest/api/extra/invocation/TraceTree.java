package com.green.forest.api.extra.invocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.green.forest.util.Util;

public class TraceTree {
	
	static final ThreadLocal<Integer> toStringLevelIndex = new ThreadLocal<Integer>();
	
	private List<TraceItem> level = new ArrayList<TraceItem>();
	
	public TraceTree() {
		super();
	}

	public TraceTree(Object... traceTree) {
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
	
	private void addItem(TraceTree trace, Object ob){
		//item
		if(ob instanceof Class){
			trace.createAndAddItem(ob);
		}
		//item's sub trace
		else if(ob instanceof List<?>){
			
			TraceTree subTrace = new TraceTree();
			trace.addSubTraceToLastItem(subTrace);
			
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
		TraceItem item = new TraceItem(clazz);
		level.add(item);
	}

	public void addSubTraceToLastItem(TraceTree subTrace) {
		if(level.isEmpty()){
			throw new IllegalStateException("can't add sub tree for empty tree");
		}
		TraceItem last = level.get(level.size()-1);
		last.addSubTrace(subTrace);
	}
	
	public TraceItem getItem(int index){
		return level.get(index);
	}
	
	public TraceItem getLastItem(){
		if(level.size() == 0){
			return null;
		}
		return level.get(level.size()-1);
	}
	
	public int getSize(){
		return level.size();
	}
	
	public List<TraceItem> getItems(){
		return new ArrayList<TraceItem>(level);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((level == null) ? 0 : level.hashCode());
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
		TraceTree other = (TraceTree) obj;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
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
		sb.append("[TraceStack:");
		
		List<TraceItem> items = Collections.emptyList();
		if(level != null){
			items = level;
		}
		
        Iterator<TraceItem> i = items.iterator();
		if (! i.hasNext()){
		    sb.append(" empty");
		} else {
			
			sb.append('\n');
			
			while(i.hasNext()) {
				
				toStringLevelIndex.set(levelIndex+1);
				TraceItem e = i.next();
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
