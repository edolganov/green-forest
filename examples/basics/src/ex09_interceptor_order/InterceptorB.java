package ex09_interceptor_order;

import actions.SomeAction;

import com.gf.Interceptor;
import com.gf.annotation.Mapping;
import com.gf.annotation.Order;
import com.gf.service.InterceptorChain;


@Order(-1)
@Mapping(SomeAction.class)
public class InterceptorB extends Interceptor<SomeAction>{

	@Override
	public void invoke(SomeAction action, InterceptorChain chain)
			throws Exception {
		
		System.out.print("B");
		chain.doNext();
		
	}

}
