package ex03_use_action_attrs;

import actions.SomeAction;

import com.gf.Interceptor;
import com.gf.annotation.Mapping;
import com.gf.service.InterceptorChain;


@Mapping(SomeAction.class)
public class SomeInterceptor extends Interceptor<SomeAction>{

	@Override
	public void invoke(SomeAction action, InterceptorChain chain)
			throws Exception {
		
		action.putAttr("some-key", "additional data");
		
		chain.doNext();
		
	}

}
