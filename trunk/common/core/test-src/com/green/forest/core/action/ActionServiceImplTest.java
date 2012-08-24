package com.green.forest.core.action;

import junit.framework.Assert;

import org.junit.Test;

import com.green.forest.api.ActionService;
import com.green.forest.api.test.action.StringAction;

public class ActionServiceImplTest extends Assert {
	
	@Test
	public void test_invoke(){
		
		ActionService actionService = new ActionServiceImpl();
		String param = "test";
		String result = actionService.invoke(new StringAction(param));
		assertEquals(param, result);
		
	}

}
