package com.green.forest.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class Filter {
	
	protected Log log = LogFactory.getLog(getClass());
	
	public abstract void invoke(FilterChain chain);

}
