package com.gf.core.engine.invocation.reader;

import org.junit.Test;

import com.gf.core.Engine;
import com.gf.core.engine.invocation.model.FirstReaderFilter;
import com.gf.core.engine.invocation.model.FirstReaderInterceptor;
import com.gf.core.engine.invocation.model.SecondReaderFilter;
import com.gf.core.engine.invocation.model.SecondReaderInterceptor;
import com.gf.core.engine.invocation.model.SubInvokeHandler;
import com.gf.core.engine.invocation.model.SubReaderInterceptor;
import com.gf.test.action.EmptyAction;
import com.gf.test.handler.EmptyHandler;
import com.gf.test.handler.StringEcho;
import com.gf.util.junit.AssertExt;

public class InvocationReaderFilterTest extends AssertExt {
	
	@Test
	public void test_sub_read(){
		
		Engine engine = new Engine();
		engine.putFilter(FirstReaderFilter.class);
		engine.putFilter(SecondReaderFilter.class);
		engine.putInterceptor(FirstReaderInterceptor.class);
		engine.putInterceptor(SecondReaderInterceptor.class);
		engine.putInterceptor(SubReaderInterceptor.class);
		engine.putHandler(SubInvokeHandler.class);
		engine.putHandler(StringEcho.class);
		engine.invoke(new EmptyAction());
	}
	
	@Test
	public void test_read(){
		
		Engine engine = new Engine();
		engine.putFilter(FirstReaderFilter.class);
		engine.putFilter(SecondReaderFilter.class);
		engine.putInterceptor(FirstReaderInterceptor.class);
		engine.putInterceptor(SecondReaderInterceptor.class);
		engine.putHandler(EmptyHandler.class);
		engine.invoke(new EmptyAction());
		
	}
	
	

}
