package com.green.forest.api;

import java.io.Serializable;


public interface GreenForestEngine {
	
	public <I extends Serializable, O extends Serializable> I invoke(Action<I,O> action);

}
