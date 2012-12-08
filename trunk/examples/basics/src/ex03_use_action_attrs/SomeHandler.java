package ex03_use_action_attrs;

import actions.SomeAction;

import com.gf.Handler;
import com.gf.annotation.Mapping;


@Mapping(SomeAction.class)
public class SomeHandler extends Handler<SomeAction>{

	@Override
	public void invoke(SomeAction action) throws Exception {
		
		String input = action.input();
		Object additionalData = action.getAttr("some-key");
		
		log.info("input: "+input+", additionalData:"+additionalData);
		
		
	}

}
