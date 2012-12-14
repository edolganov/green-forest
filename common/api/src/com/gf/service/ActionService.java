package com.gf.service;

import com.gf.Action;
import com.gf.Filter;
import com.gf.Handler;
import com.gf.Interceptor;
import com.gf.exception.ExceptionWrapper;
import com.gf.exception.invoke.InvocationException;

/**
 * Interface for invoking Actions.
 * Main implementaion is {@link com.gf.core.Engine}.
 *
 * @author Evgeny Dolganov
 * @see Action
 * @see Handler
 * @see Interceptor
 * @see Filter
 * @see com.gf.core.Engine
 *
 */
public interface ActionService {
	
	/**
	 * Handle the {@link Action} object.
	 * <br>Use {@link Handler}, {@link Interceptor}, {@link Filter} for this.
	 * <p>The order of processing:
	 * <ul>
	 * <li>Filters</li>
	 * <li>Interceptors</li>
	 * <li>Handler</li>
	 * </ul>
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
	 * @return <tt>Action</tt> output object.
	 * <br><b>Note:</b> This return value is the equivalent of code {@code action.getOutput()} after the invoke method.
	 * @throws InvocationException <tt>Engine</tt>'s processing exception
	 * @throws ExceptionWrapper wrapper of non-runtime <tt>Exception</tt> from handler's body
	 * @throws RuntimeException runtime <tt>Exception</tt> from handler's body
	 * @see #invokeUnwrap(Action)
	 * @see Action
	 * @see Handler
	 * @see Interceptor
	 * @see Filter
	 */
	<I, O> O invoke(Action<I,O> action) throws InvocationException, ExceptionWrapper, RuntimeException;
	
	
	/**
	 * This method is {@link #invoke(Action)} analog with unwrap non-runtime exceptions.
	 * <p>Example:
	 * <pre>
	 * &#064;Mapping(SomeAction.class)
	 * public class ActionHandlerWithException extends Handler&lt;SomeAction&gt;{
	 * 
	 *   public void invoke(SomeAction action) throws Exception {
	 *     //not-runtime exception
	 *     throw new Exception("test expection");
	 *   }
	 *   
	 * }
	 * 
	 * ...
	 * 
	 * //use invokeUnwrap
	 * try {
	 *   service.invokeUnwrap(new SomeAction());
	 * }catch (Exception e) {
	 *   System.out.println(e);
	 * }
	 * 
	 * //use invoke
	 * service.invoke(new SomeAction()); //throws ExceptionWrapper
	 * </pre>
	 * 
	 * @see #invoke(Action)
	 */
	<I, O> O invokeUnwrap(Action<I,O> action) throws InvocationException, Exception;

}
