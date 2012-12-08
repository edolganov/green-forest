package ex04_subInvoke;

import com.gf.Handler;
import com.gf.annotation.Mapping;

@Mapping(OtherAction.class)
public class OtherHandler extends Handler<OtherAction>{

	@Override
	public void invoke(OtherAction action) throws Exception {
		
		String input = action.input();
		
		if(input == null || input.length() == 0){
			action.setOutput(false);
		} else {
			action.setOutput(true);
		}
		
	}

}
