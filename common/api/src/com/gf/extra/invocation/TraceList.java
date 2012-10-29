package com.gf.extra.invocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.gf.util.Util;

public class TraceList {
	
	static final ThreadLocal<Integer> toStringLevelIndex = new ThreadLocal<Integer>();
	
	private List<TraceItem> list = new ArrayList<TraceItem>();
	
	public TraceList() {
		super();
	}

	public TraceList(Object... traceTree) {
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
	
	private void addItem(TraceList trace, Object ob){
		//item
		if(ob instanceof Class){
			trace.createAndAddItem(ob);
		}
		//item's sub trace
		else if(ob instanceof List<?>){
			
			TraceList subTrace = new TraceList();
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
		TraceItem item = new TraceItem(clazz);
		list.add(item);
	}

	public void addSubListToLastItem(TraceList subTrace) {
		if(list.isEmpty()){
			throw new IllegalStateException("can't add sub tree for empty tree");
		}
		TraceItem last = list.get(list.size()-1);
		last.addSubList(subTrace);
	}
	
	public TraceItem getItem(int index){
		return list.get(index);
	}
	
	public TraceItem getLastItem(){
		if(list.size() == 0){
			return null;
		}
		return list.get(list.size()-1);
	}
	
	public int getSize(){
		return list.size();
	}
	
	public List<TraceItem> getItems(){
		return new ArrayList<TraceItem>(list);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
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
		TraceList other = (TraceList) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
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
		sb.append("[TraceList:");
		
		List<TraceItem> items = Collections.emptyList();
		if(list != null){
			items = list;
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
