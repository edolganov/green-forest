package jdbc.storage.model;

import com.gf.Action;
import com.gf.Interceptor;
import com.gf.annotation.Mapping;
import com.gf.exception.TestRuntimeException;
import com.gf.service.InterceptorChain;

@Mapping(Action.class)
public class ThrowExceptionAfterHandler extends Interceptor<Action<?,?>>{

	@Override
	public void invoke(Action<?, ?> action, InterceptorChain chain)
			throws Exception {
		chain.doNext();
		throw new TestRuntimeException();
		
	}

}
