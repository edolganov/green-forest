package com.gf.components.mybatis;

import org.junit.Test;

import com.gf.components.mybatis.model.HandlerWithNoSettings;
import com.gf.components.mybatis.model.HandlerWithSettings;
import com.gf.core.Engine;
import com.gf.test.action.EmptyAction;

public class SqlSessionInInvokeTest extends AbstractTest {
	
	
	@Test
	public void test_invoke_with_settings(){
		
		Engine engine = new Engine();
		engine.addToContext(dataSource);
		engine.addToContext(sqlSessionFactory);
		engine.putFilter(SqlSessionInInvoke.class);
		engine.putHandler(HandlerWithSettings.class);
		
		engine.invoke(new EmptyAction());
		
	}
	
	
	@Test
	public void test_invoke_default(){
		
		Engine engine = new Engine();
		engine.addToContext(dataSource);
		engine.addToContext(sqlSessionFactory);
		engine.putFilter(SqlSessionInInvoke.class);
		engine.putHandler(HandlerWithNoSettings.class);
		
		engine.invoke(new EmptyAction());
		
	}
	

}
