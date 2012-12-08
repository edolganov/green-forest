package com.gf;

import com.gf.log.Log;
import com.gf.log.LogFactory;

/**
 * Base type for <tt>Handler</tt>, <tt>Interceptor</tt>, <tt>Filter</tt>
 *
 * @author Evgeny Dolganov
 * @see Handler
 * @see Interceptor
 * @see Filter
 *
 */
public abstract class InvocationObject {
	
	/**
	 * default logger
	 */
	protected Log log = LogFactory.getLog(getClass());
	
}
