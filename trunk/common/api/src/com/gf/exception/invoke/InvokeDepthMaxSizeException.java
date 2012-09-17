package com.gf.exception.invoke;

import com.gf.key.core.InvokeDepth;

public class InvokeDepthMaxSizeException extends InvocationException {

	public InvokeDepthMaxSizeException(int maxSize) {
		super("Max invoke depth: "+maxSize+". You can change it by key " + InvokeDepth.class.getName());
	}
	
	

}
