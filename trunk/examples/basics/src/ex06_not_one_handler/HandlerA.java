package ex06_not_one_handler;

import com.gf.Handler;
import com.gf.annotation.Mapping;

import ex01_invoke.SomeAction;

@Mapping(SomeAction.class)
public class HandlerA extends Handler<SomeAction>{

	@Override
	public void invoke(SomeAction action) throws Exception {
		
	}

}
