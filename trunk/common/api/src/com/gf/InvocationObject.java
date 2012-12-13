package com.gf;

import com.gf.log.Log;
import com.gf.log.LogFactory;
import com.gf.service.ConfigService;

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
	 * Default logger
	 */
	protected Log log = LogFactory.getLog(getClass());
	
	/**
	 * Config service
	 */
	protected ConfigService config;

	
	public void setConfigService(ConfigService config) {
		this.config = config;
	}
	
	
	
}
