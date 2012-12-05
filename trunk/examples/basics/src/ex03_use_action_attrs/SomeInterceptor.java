package ex03_use_action_attrs;

import com.gf.Interceptor;
import com.gf.annotation.Mapping;
import com.gf.service.InterceptorChain;

import ex01_invoke.SomeAction;

@Mapping(SomeAction.class)
public class SomeInterceptor extends Interceptor<SomeAction>{

	@Override
	public void invoke(SomeAction action, InterceptorChain chain)
			throws Exception {
		
		action.putAttr("some-key", "additional data");
		
		chain.doNext();
		
	}

}
