package com.green.forest.api.extra.invocation;

import java.util.ArrayList;
import java.util.List;

import com.green.forest.util.Util;

public class TraceTree {
	
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
		//signle
		if(ob instanceof Class){
			trace.createAndAddItem(ob);
		}
		//complex
		else if(ob instanceof List<?>){
			List<?> itemAndSubTraces = (List<?>)ob;
			Object itemOb = itemAndSubTraces.get(0);
			if(itemOb instanceof Class){
				trace.createAndAddItem(itemOb);
			} else {
				throw new IllegalStateException("invalid type of elem: "+itemOb);
			}
			for(int i=1; i < itemAndSubTraces.size(); ++i){
				
				TraceTree subTrace = new TraceTree();
				trace.addSubTraceToLastItem(subTrace);
				
				Object subTraceObj = itemAndSubTraces.get(i);
				addItem(subTrace, subTraceObj);
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

}
