package ex08_interceptor;

import actions.SomeAction;

import com.gf.Handler;
import com.gf.annotation.Mapping;


@Mapping(SomeAction.class)
public class SomeHandler extends Handler<SomeAction>{

	@Override
	public void invoke(SomeAction action) throws Exception {
		
		String input = action.input();
		log.info("input: "+input);
		action.setOutput(input);
		
	}

}
