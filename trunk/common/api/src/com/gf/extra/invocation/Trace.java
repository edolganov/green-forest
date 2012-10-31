package com.gf.extra.invocation;


public class Trace extends TraceLevel {
	
	private Object owner;
	
	public Trace() {
		super();
		this.owner = null;
	}

	public Trace(Object owner, Object... traceTree) {
		super(traceTree);
		this.owner = owner;
	}

	public Object getOwner() {
		return owner;
	}
	
	@Override
	public String toStringCurObject() {
		return getClass().getSimpleName()+" [owner="+owner+", childrenCount="+children.size()+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
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
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}
	

}
