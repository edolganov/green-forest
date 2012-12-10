package spring;

import org.junit.Test;

import com.gf.extra.trace.Trace;
import com.gf.key.TraceHandlers;

import example.common.action.GetDocsCount;

public class AppTest extends AbstractSpringTest {
	
	@Test
	public void test_trace(){
		
		GetDocsCount action = new GetDocsCount();
		app.invoke(action);
		
		Trace trace = TraceHandlers.getTrace(action);
		assertNotNull(trace);
		
	}

}
