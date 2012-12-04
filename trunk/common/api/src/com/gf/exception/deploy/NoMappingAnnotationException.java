package com.gf.exception.deploy;

import com.gf.MappingObject;
import com.gf.annotation.Mapping;

/**
 * <tt>MappingObject</tt> do not contains <tt>@Mapping</tt> annotation.
 * <p>Example:
 * <pre>
 * 
 * //@Mapping(SomeAction.class)
 * public class HandlerWithNoMapping extends Handler<SomeAction>{ 
 * 	... 
 * }
 * 
 * Engine engine = new Engine();
 * engine.putHandler(HandlerWithNoMapping.class); //throws NoMappingAnnotationException
 * 
 * </pre>
 * @author jenya.dolganov
 * @see Mapping
 * @see MappingObject
 *
 */
public class NoMappingAnnotationException extends DeployException {

	private static final long serialVersionUID = 3050383810011881818L;

	public NoMappingAnnotationException(Class<?> handler) {
		super("no mapping for ["+handler+"]");
	}

}
