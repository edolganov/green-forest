package ex04_subInvoke;

import actions.OtherAction;
import actions.SomeAction;

import com.gf.Handler;
import com.gf.annotation.Mapping;


@Mapping(SomeAction.class)
public class SomeHandler extends Handler<SomeAction>{

	@Override
	public void invoke(SomeAction action) throws Exception {
		
		String input = action.input();
		boolean valid = subInvoke(new OtherAction(input));
		if(valid){
			action.setOutput("valid");
		} else {
			action.setOutput("error!");
		}
	}
}
