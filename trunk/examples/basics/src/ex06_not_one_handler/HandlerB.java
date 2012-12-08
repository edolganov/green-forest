package ex06_not_one_handler;

import actions.SomeAction;

import com.gf.Handler;
import com.gf.annotation.Mapping;


@Mapping(SomeAction.class)
public class HandlerB extends Handler<SomeAction>{

	@Override
	public void invoke(SomeAction action) throws Exception {
		
	}

}
