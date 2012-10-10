package com.gf.core.engine.deploy.scan.subpackage;

import com.gf.Action;
import com.gf.Filter;
import com.gf.annotation.Order;
import com.gf.service.FilterChain;

@Order(Integer.MIN_VALUE)
public class SubScanFilter extends Filter {

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		chain.doNext();
		
	}

}
