package com.green.forest.core.test.model.handler;

import com.green.forest.api.Handler;
import com.green.forest.api.test.action.EmptyAction;

public class HandlerWithoutMapping extends Handler<EmptyAction>{

	@Override
	public void invoke(EmptyAction action) throws Exception {
		throw new IllegalStateException("no invoke expected");
	}

}
