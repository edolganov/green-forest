package com.gf.core.engine.handler.model;

import com.gf.Handler;
import com.gf.annotation.Mapping;
import com.gf.test.action.EmptyAction;
import com.gf.util.junit.AssertExt;


@Mapping(EmptyAction.class)
public class GetConfigHandler extends Handler<EmptyAction>{

	@Override
	public void invoke(EmptyAction action) throws Exception {
		
		String value = config.getConfig(new TestConfig());
		AssertExt.assertEquals("test", value); 
		
	}

}
