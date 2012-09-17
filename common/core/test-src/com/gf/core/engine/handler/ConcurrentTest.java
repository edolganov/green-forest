package com.gf.core.engine.handler;

import org.junit.Ignore;
import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.EngineTest;
import com.gf.exception.invoke.HandlerNotFoundException;
import com.gf.test.action.StringAction;
import com.gf.test.handler.StringEcho;
import com.gf.util.test.concurrent.ThreadRacer;
import com.gf.util.test.concurrent.ThreadsRace;

public class ConcurrentTest extends EngineTest {
	
	@Ignore
	@Test(expected=HandlerNotFoundException.class)
	public void test_no_handler_concurrent(){
		
		final Engine engine = new Engine();
		
		ThreadsRace race = new ThreadsRace();
		
		race.startFromBegin(new ThreadRacer(){

			@Override
			public void invoke() throws Exception {
				
				engine.putHandler(StringEcho.class);
				fireAndWait("handler added", "handler removed");
				engine.invoke(new StringAction("test"));
			}
			
		});
		
		race.startWithEvent("handler added", new ThreadRacer() {
			
			@Override
			public void invoke() throws Exception {
				
				//engine.removeHandler(StringEcho.class);
				fire("handler removed");
			}
		});
		
		race.invokeWithRuntimeExceptions();
		
	}

}
