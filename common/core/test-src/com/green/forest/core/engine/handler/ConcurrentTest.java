package com.green.forest.core.engine.handler;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.green.forest.api.exception.invoke.HandlerNotFoundException;
import com.green.forest.api.test.action.StringAction;
import com.green.forest.api.test.handler.StringEcho;
import com.green.forest.core.Engine;
import com.green.forest.util.test.concurrent.ThreadRacer;
import com.green.forest.util.test.concurrent.ThreadsRace;

public class ConcurrentTest extends Assert {
	
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
				
				engine.removeHandler(StringEcho.class);
				fire("handler removed");
			}
		});
		
		race.invokeWithRuntimeExceptions();
		
	}

}
