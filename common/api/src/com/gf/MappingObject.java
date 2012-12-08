package com.gf;

import com.gf.service.InvocationService;

/**
 * Base type for <tt>Handler</tt>, <tt>Interceptor</tt>
 *
 * @author Evgeny Dolganov
 * @see Handler
 * @see Interceptor
 *
 */
public abstract class MappingObject extends InvocationObject {
	
	private InvocationService invocation;
	
	/**
	 * sub invoke the action.
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
	protected <I,O> O subInvoke(Action<I, O> action) throws Exception {
		return (O)invocation.subInvoke(action);
	}

	public void setInvocation(InvocationService invocation) {
		this.invocation = invocation;
	}
	

}
