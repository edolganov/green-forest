package ex01_simple_create;

import com.gf.Handler;
import com.gf.annotation.Mapping;

@Mapping(SomeAction.class)
public class SomeActonHandler extends Handler<SomeAction>{

	@Override
	public void invoke(SomeAction action) throws Exception {
		
		String input = action.getInput();
		
		log.info("input is "+input);
		
		action.setOutput(input);
		
	}

}
