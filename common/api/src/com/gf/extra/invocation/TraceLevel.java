package com.gf.extra.invocation;

import java.util.ArrayList;
import java.util.List;

import com.gf.util.Util;

public class TraceLevel extends TraceElement {
	
	private List<TraceLevelItem> children = new ArrayList<TraceLevelItem>();
	
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
	
	private void addItem(TraceLevel level, Object ob){
		//item
		if(ob instanceof Class){
			TraceLevelItem item = new TraceLevelItem(ob);
			addChild(item);
		}
		//item's sub trace
		else if(ob instanceof List<?>){
			
			TraceLevel subLevel = new TraceLevel();
			level.addSubChildToLastChild(subLevel);
			
			List<?> subItems = (List<?>)ob;
			for(int i=0; i < subItems.size(); ++i){
				Object subItem = subItems.get(i);
				addItem(subLevel, subItem);
			}
		}
		//unknown
		else {
			throw new IllegalArgumentException("invalid type of elem: "+ob);
		}
	}

	private void addSubChildToLastChild(TraceElement child) {
		if(children.isEmpty()){
			throw new IllegalStateException("can't add sub tree for empty tree");
		}
		TraceLevelItem last = children.get(children.size()-1);
		last.addChild(child);
	}
	
	public List<TraceLevelItem> getChildrenItems(){
		return new ArrayList<TraceLevelItem>(children);
	}
	
	@Override
	public List<TraceElement> getChildren() {
		ArrayList<TraceElement> out = new ArrayList<TraceElement>();
		for (TraceLevelItem item : children) {
			out.add(item);
		}
		return out;
	}
	
	@Override
	public void addChild(TraceElement child) {
		if( child instanceof TraceLevelItem){
			children.add((TraceLevelItem)child);
		} else {
			throw new IllegalStateException("Expected child of type ["+TraceLevelItem.class.getName()+"] but was: "+child);
		}
		
	}
	
	@Override
	public String toStringCurObject() {
		return getClass().getSimpleName()+" [childrenCount="+children.size()+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
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
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		return true;
	}
	


}
