package ex12_inject;

import actions.SomeAction;

import com.gf.Handler;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;


@Mapping(SomeAction.class)
public class SomeHandler extends Handler<SomeAction>{
	
	@Inject
	SomeService service;

	@Override
	public void invoke(SomeAction action) throws Exception {
		
		service.someMethod();
		
	}

}
