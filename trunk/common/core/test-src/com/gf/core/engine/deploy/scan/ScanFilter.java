package com.gf.core.engine.deploy.scan;

import com.gf.Action;
import com.gf.Filter;
import com.gf.service.FilterChain;

public class ScanFilter extends Filter {

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		chain.doNext();
		
	}

}
