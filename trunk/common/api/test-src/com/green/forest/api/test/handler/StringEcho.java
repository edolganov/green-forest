package com.green.forest.api.test.handler;

import com.green.forest.api.Handler;
import com.green.forest.api.annotation.Mapping;
import com.green.forest.api.test.action.StringAction;

@Mapping(StringAction.class)
public class StringEcho extends Handler<StringAction>{

	@Override
	public void invoke(StringAction action) throws Exception {
		String input = action.getInput();
		action.setOutput(input);
	}

}
