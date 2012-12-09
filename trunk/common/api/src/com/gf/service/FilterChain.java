package com.gf.service;

import com.gf.Action;
import com.gf.Filter;

/**
 * Interface to call next filter or interceptor or handler.
 * Use in {@link Filter#invoke(Action, FilterChain)}
 *
 * @author Evgeny Dolganov
 *
 */
public interface FilterChain {
	
	/**
	 * call next filter or interceptor or handler
	 */
	void doNext() throws Exception; 

}
