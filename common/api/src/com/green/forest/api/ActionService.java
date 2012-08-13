package com.green.forest.api;

import java.io.Serializable;

import com.green.forest.api.exception.InvocationException;


public interface ActionService {
	
	<I extends Serializable, O extends Serializable> I invoke(Action<I,O> action) throws InvocationException;

}
