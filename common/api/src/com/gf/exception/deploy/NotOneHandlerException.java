package com.gf.exception.deploy;

import java.util.Set;

/**
 * There is already some <tt>Handler</tt> for mapped <tt>Action</tt>.
 * <p>Example:
 * <pre>
 * &#064;Mapping(SomeAction.class)
 * public class HandlerA extends Handler&lt;SomeAction&gt;{ ... }
 * 
 * &#064;Mapping(SomeAction.class)
 * public class HandlerB extends Handler&lt;SomeAction&gt;{ ... }
 * 
 * Engine engine = new Engine();
 * engine.putHandler(HandlerA.class);
 * engine.putHandler(HandlerB.class); //throws NotOneHandlerException
 * 
 * </pre>
 *
 * @author jenya.dolganov
 *
 */
public class NotOneHandlerException extends DeployException {
	
	private static final long serialVersionUID = 8460269856330004245L;

	public NotOneHandlerException(Class<?> target, Set<Class<?>> handlers) {
		super("target ["+target+"] has many handlers "+handlers);
	}
	
	

}
