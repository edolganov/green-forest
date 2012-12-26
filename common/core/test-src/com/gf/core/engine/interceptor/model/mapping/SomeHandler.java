package com.gf.core.engine.interceptor.model.mapping;

import com.gf.Action;
import com.gf.Handler;
import com.gf.annotation.Mapping;
import com.gf.util.junit.AssertExt;

@Mapping(SomeInterface.class)
public class SomeHandler extends Handler<Action<?,?>>{

	@Override
	public void invoke(Action<?, ?> action) throws Exception {
		
		AssertExt.assertTrue(action instanceof SomeInterface);
		
	}

}
