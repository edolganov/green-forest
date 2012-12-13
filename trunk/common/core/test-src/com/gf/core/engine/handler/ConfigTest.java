package com.gf.core.engine.handler;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.AbstractEngineTest;
import com.gf.core.engine.handler.model.GetConfigHandler;
import com.gf.core.engine.handler.model.TestConfig;
import com.gf.test.action.EmptyAction;

public class ConfigTest extends AbstractEngineTest {
	
	@Test
	public void test_getValue(){
		
		Engine engine = new Engine();
		engine.putHandler(GetConfigHandler.class);
		engine.setConfig(TestConfig.class, "test");
		engine.invoke(new EmptyAction());
		
	}

}
