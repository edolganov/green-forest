package com.gf.extra.invocation;

import java.util.LinkedList;
import java.util.List;

import com.gf.util.Util;

public class TraceUtil {
	
	public static String toString(TraceElement root){
		
		StringBuilder sb = new StringBuilder();
		appendElem(sb, root);
		
		LinkedList<QueueItem> queue = new LinkedList<QueueItem>();
		List<TraceElement> rootChildren = root.getChildren();
		if( ! Util.isEmpty(rootChildren)){
			for (TraceElement child : rootChildren) {
				queue.addLast(new QueueItem(child, 1));
			}
		}
		
		while( ! queue.isEmpty()){
			QueueItem item = queue.removeFirst();
			TraceElement elem = item.elem;
			int level = item.level;
			appendLine(sb);
			appendTabs(sb, level);
			appendElem(sb, elem);
			
			List<TraceElement> children = elem.getChildren();
			if( ! Util.isEmpty(children)){
				int nextLevel = level+1;
				for (TraceElement child : children) {
					queue.addLast(new QueueItem(child, nextLevel));
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
	}




	private static class QueueItem {
		
		TraceElement elem;
		int level;
		
		public QueueItem(TraceElement elem, int level) {
			super();
			this.elem = elem;
			this.level = level;
		}
		
	}

}
