package ex02_invokeUnwrap;

import actions.SomeAction;

import com.gf.Handler;
import com.gf.annotation.Mapping;


@Mapping(SomeAction.class)
public class ActionHandlerWithException extends Handler<SomeAction>{

	@Override
	public void invoke(SomeAction action) throws Exception {
		//not-runtime exception
		throw new Exception("test expection");
	}

}
