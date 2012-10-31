package com.gf.extra.invocation;

import java.util.List;

public abstract class TraceElement {
	
	
	private long startTime;
	private long endTime;
	private InvocaitonEndStatus endStatus;
	
	public void start(){
		startTime = System.currentTimeMillis();
	}
	
	public void stop(){
		endTime = System.currentTimeMillis();
	}
	
	public long getDuration(){
		return endTime - startTime;
	}
	
	public void setEndStatus(InvocaitonEndStatus endStatus){
		this.endStatus = endStatus;
	}
	
	public InvocaitonEndStatus getEndStatus() {
		return endStatus;
	}
	
	

	public abstract List<TraceElement> getChildren();
	
	abstract String toStringCurObject();
	
	@Override
	public String toString() {
		return TraceUtil.toString(this);
	}

}
