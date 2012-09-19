package com.gf.test.handler;

import com.gf.Handler;
import com.gf.annotation.Mapping;
import com.gf.test.action.EmptyAction;

@Mapping(EmptyAction.class)
public class EmptyHandler extends Handler<EmptyAction>{

	@Override
	public void invoke(EmptyAction action) throws Exception {
		
	}

}
