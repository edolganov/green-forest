package ex11_addToInvocationContext;

import com.gf.Action;
import com.gf.Filter;
import com.gf.service.FilterChain;


public class SomeFilter extends Filter {
	
	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		SomeService service = new SomeService();
		addToInvocationContext(service);
		chain.doNext();
		
	}

}
