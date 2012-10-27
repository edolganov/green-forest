package com.gf.service;

import com.gf.Action;
import com.gf.exception.ExceptionWrapper;
import com.gf.exception.invoke.InvocationException;


public interface ActionService {
	
	<I, O> O invoke(Action<I,O> action) throws InvocationException, ExceptionWrapper, RuntimeException;
	
	<I, O> O invokeUnwrap(Action<I,O> action) throws InvocationException, Exception;

}
