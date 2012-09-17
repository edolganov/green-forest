package com.gf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Action<I extends Serializable, O extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private I input;
	private O output;
	private Map<String, Object> attrs;
	
	public Action() {
		super();
	}

	public Action(I input) {
		super();
		this.input = input;
	}
	

	public I getInput() {
		return input;
	}

	public void setInput(I input) {
		this.input = input;
	}

	public O getOutput() {
		return output;
	}

	public void setOutput(O output) {
		this.output = output;
	}
	
	public void putAttr(String key, Object val){
		if(attrs == null){
			attrs = new HashMap<String, Object>();
		}
		attrs.put(key, val);
	}
	
	public Object getAttr(String key){
		if(attrs == null){
			return null;
		}
		return attrs.get(key);
	}
	
	public Map<String, Object> getAllAttr(){
		if(attrs == null){
			return new HashMap<String, Object>();
		}
		return new HashMap<String, Object>(attrs);
	}
	
	
	@Override
    public String toString() {
		return this.getClass().getSimpleName()+"@"+Integer.toHexString(hashCode())+" [input=" + input + ", output=" + output + "]";
    }

}
