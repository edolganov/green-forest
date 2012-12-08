package ex09_interceptor_order;

import actions.SomeAction;

import com.gf.Interceptor;
import com.gf.annotation.Mapping;
import com.gf.service.InterceptorChain;


@Mapping(SomeAction.class)
public class InterceptorC extends Interceptor<SomeAction>{

	@Override
	public void invoke(SomeAction action, InterceptorChain chain)
			throws Exception {
		
		System.out.print("C");
		chain.doNext();
		
	}

}
