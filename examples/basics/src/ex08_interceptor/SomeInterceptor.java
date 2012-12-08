package ex08_interceptor;

import actions.SomeAction;

import com.gf.Interceptor;
import com.gf.annotation.Mapping;
import com.gf.service.InterceptorChain;


@Mapping(SomeAction.class)
public class SomeInterceptor extends Interceptor<SomeAction>{

	@Override
	public void invoke(SomeAction action, InterceptorChain chain)
			throws Exception {
		
		//before handler
		String input = action.input();
		String newInput = "changed input [ "+input+" ]";
		action.setInput(newInput);
		
		//next interceptor or handler
		chain.doNext();
		
		//after handler
		String output = action.getOutput();
		String newOutput = "changed output [ "+output+" ]";
		action.setOutput(newOutput);
		
	}

}
