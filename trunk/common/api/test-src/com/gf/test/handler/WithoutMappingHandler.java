package com.gf.test.handler;

import com.gf.Handler;
import com.gf.test.action.EmptyAction;

public class WithoutMappingHandler extends Handler<EmptyAction>{

	@Override
	public void invoke(EmptyAction action) throws Exception {
		throw new IllegalStateException("no invoke expected");
	}

}
