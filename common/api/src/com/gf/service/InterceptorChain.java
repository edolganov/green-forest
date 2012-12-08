package com.gf.service;

import com.gf.Action;
import com.gf.Interceptor;

/**
 * Interface to call next interceptor or handler.
 * Use in {@link Interceptor#invoke(Action, InterceptorChain)}
 *
 * @author Evgeny Dolganov
 *
 */
public interface InterceptorChain {
	
	/**
	 * call next interceptor or handler
	 */
	void doNext() throws Exception;

}
