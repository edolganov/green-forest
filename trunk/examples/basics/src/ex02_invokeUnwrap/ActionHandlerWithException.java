package ex02_invokeUnwrap;

import com.gf.Handler;
import com.gf.annotation.Mapping;

import ex01_invoke.SomeAction;

@Mapping(SomeAction.class)
public class ActionHandlerWithException extends Handler<SomeAction>{

	@Override
	public void invoke(SomeAction action) throws Exception {
		//not-runtime exception
		throw new Exception("test expection");
	}

}
