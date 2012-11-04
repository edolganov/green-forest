package com.gf.extra.invocation;

import java.util.LinkedList;
import java.util.List;

import com.gf.util.Util;

public class TraceUtil {
	
	public static String toString(TraceElement root){
		
		StringBuilder sb = new StringBuilder();
		appendElem(sb, root);
		
		LinkedList<StackItem> stack = new LinkedList<StackItem>();
		List<TraceElement> rootChildren = root.getChildren();
		if( ! Util.isEmpty(rootChildren)){
			for(int i=rootChildren.size()-1; i>-1; i--){
				TraceElement child = rootChildren.get(i);
				stack.addFirst(new StackItem(child, 1));
			}
		}
		
		while( ! stack.isEmpty()){
			StackItem item = stack.removeFirst();
			TraceElement elem = item.elem;
			int level = item.level;
			appendLine(sb);
			appendTabs(sb, level);
			appendElem(sb, elem);
			
			List<TraceElement> children = elem.getChildren();
			if( ! Util.isEmpty(children)){
				int nextLevel = level+1;
				for(int i=children.size()-1; i>-1; i--){
					TraceElement child = children.get(i);
					stack.addFirst(new StackItem(child, nextLevel));
				}
			}
		}
		
		return sb.toString();
	}

	private static void appendLine(StringBuilder sb) {
		sb.append('\n');
	}
	
	private static void appendTabs(StringBuilder sb, int count) {
		for (int i = 0; i < count; i++) {
			sb.append('\t');
		}
	}
	
	private static void appendElem(StringBuilder sb, TraceElement elem) {
		sb.append(elem.toStringCurObject());
		sb.append(" (duration=").append(elem.getDuration()).append("ms");
		sb.append(", endStatus=").append(elem.getEndStatus()).append(")");
	}




	private static class StackItem {
		
		TraceElement elem;
		int level;
		
		public StackItem(TraceElement elem, int level) {
			super();
			this.elem = elem;
			this.level = level;
		}
		
	}

}
