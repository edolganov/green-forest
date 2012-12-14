package com.gf.service;

import com.gf.Action;
import com.gf.Handler;
import com.gf.Interceptor;

/**
 * Handler's and Interceptor's invocation controll service.
 *
 * @author Evgeny Dolganov
 * @see Handler
 * @see Interceptor
 *
 */
public interface InvocationService {
	
	/**
	 * Sub invoke the action.
	 * <p>Interceptors and Handlers can call <tt>subInvoke</tt> method.
	 * <br>In this case the order of processing:
	 * <ul>
	 * <li>Filters <b>(was called only once)</b></li>
	 * <li>Interceptors
	 * 		<ul>
	 * 			<li>Sub Interceptors</li>
	 * 			<li>Sub Handler</li>
	 * 		</ul>
	 * </li>
	 * <li>Handler
	 * 		<ul>
	 * 			<li>Sub Interceptors</li>
	 * 			<li>Sub Handler</li>
	 * 		</ul>
	 * </li>
	 * </ul>
	 */
	<I,O> O subInvoke(Action<I, O> action) throws Exception;

}
