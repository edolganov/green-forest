package com.gf.extra.invocation;

import java.util.List;

public abstract class TraceElement {
	
	public abstract List<TraceElement> getChildren();
	
	abstract String toStringCurObject();
	
	@Override
	public String toString() {
		return TraceUtil.toString(this);
	}

}
