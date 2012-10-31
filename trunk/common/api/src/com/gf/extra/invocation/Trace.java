package com.gf.extra.invocation;

import java.util.List;

import com.gf.util.Util;

public class Trace extends TraceElement {
	
	private Object owner;
	private TraceLevel level;
	
	public Trace() {
		this.owner = null;
		this.level = new TraceLevel();
	}

	public Trace(Object owner, Object... traceTree) {
		this.owner = owner;
		this.level = new TraceLevel(traceTree);
	}
	
	@Override
	public void addChild(TraceElement child) {
		level.addChild(child);
	}
	

	@Override
	public List<TraceElement> getChildren() {
		return Util.list((TraceElement)level);
	}

	public Object getOwner() {
		return owner;
	}

	public TraceLevel getLevel() {
		return level;
	}
	
	@Override
	public String toStringCurObject() {
		return getClass().getSimpleName()+" [owner="+owner+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		Trace other = (Trace) obj;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}
	

}
