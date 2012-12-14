package com.gf.service;

import java.util.Collection;

import com.gf.Handler;
import com.gf.annotation.Inject;

/**
 * Interface for editing handler's context.
 * Main implementaion is {@link com.gf.core.Engine}.
 *
 * @author Evgeny Dolganov
 * @see Handler
 * @see Inject
 * @see com.gf.core.Engine
 */
public interface ContextService {
	
	/**
	 * Add some object to context.
	 * So this object can be injected into filters, interceptors, handlers.
	 * @see Inject
	 */
	void addToContext(Object ob);
	
	/**
	 * Analog of multi call of {@link #addToContext(Object)}.
	 * For example for JavaBean logic.
	 */
	void setContextObjects(Collection<Object> objects);

}
