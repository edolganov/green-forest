package com.green.forest.api;

import java.io.Serializable;

public abstract class Action<I extends Serializable, O extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private I input;
	private O output;
	
	public Action() {
		super();
	}

	public Action(I input) {
		super();
		this.input = input;
	}
	
	
	public I input() {
		return input;
	}

	public void output(O output) {
		this.output = output;
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
	
	@Override
    public String toString() {
		return this.getClass().getSimpleName()+"@"+Integer.toHexString(hashCode())+" [input=" + input + ", output=" + output + "]";
    }

}
