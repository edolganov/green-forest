package com.gf.core.engine.interceptor;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.AbstractEngineTest;
import com.gf.core.engine.interceptor.model.mapping.SomeAction;
import com.gf.core.engine.interceptor.model.mapping.SomeHandler;
import com.gf.core.engine.interceptor.model.mapping.SomeInterceptor;

public class MappingTest extends AbstractEngineTest {
	
	@Test
	public void test_mapping_interface(){
		
		Engine engine = new Engine();
		engine.putHandler(SomeHandler.class);
		engine.putInterceptor(SomeInterceptor.class);
		engine.invoke(new SomeAction());
		
		
	}
	

}
