package com.gf.components.mybatis;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.gf.Action;
import com.gf.annotation.Inject;
import com.gf.annotation.Order;
import com.gf.extra.invocation.reader.InvocationReaderFilter;
import com.gf.service.FilterChain;
import com.gf.util.Util;

@Order(Order.SYSTEM_ORDER)
public class SqlSessionInInvoke extends InvocationReaderFilter {
	
	@Inject
	SqlSessionFactory sessionFactory;

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		
		
	}
	
	private SqlSession createSession(Object handler){
		SqlSessionSettings settings = handler.getClass().getAnnotation(SqlSessionSettings.class);
		if(Util.isEmpty(settings)){
			return sessionFactory.openSession();
		}else {
			ExecutorType execType = settings.execType();
			if(Util.isEmpty(execType)){
				return sessionFactory.openSession();
			} else {
				return sessionFactory.openSession(execType);
			}
		}
		
	}

}
