package ex13_scan;

import java.util.Date;

import com.gf.Action;
import com.gf.Filter;
import com.gf.service.FilterChain;


public class SomeFilter extends Filter {

	
	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		Date begin = new Date();
		log.info("begin: "+begin);
		
		try {
			//next handler
			chain.doNext();
		}finally {
			long worktime = System.currentTimeMillis() - begin.getTime();
			log.info("worktime: "+worktime+"ms");
		}
		
	}

}
