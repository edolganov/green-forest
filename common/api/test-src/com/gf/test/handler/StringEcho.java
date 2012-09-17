package com.gf.test.handler;

import com.gf.Handler;
import com.gf.annotation.Mapping;
import com.gf.test.action.StringAction;

@Mapping(StringAction.class)
public class StringEcho extends Handler<StringAction>{

	@Override
	public void invoke(StringAction action) throws Exception {
		String input = action.getInput();
		action.setOutput(input);
	}

}
