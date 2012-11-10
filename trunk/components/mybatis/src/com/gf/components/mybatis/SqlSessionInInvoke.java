package com.gf.components.mybatis;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.gf.Action;
import com.gf.InvocationObject;
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
		
		SqlSessionSettings settings = findSettingsInNextObjects();
		SqlSession session = createSession(settings);
		try {
			
			invocationContext.addToInvocationContext(session);
			chain.doNext();
			
		}catch (Exception e) {
			throw e;
		} finally {
			try {
				session.close();
			}catch (Exception ex) {
				log.error("can't close session", ex);
			}
		}
		
	}

	private SqlSessionSettings findSettingsInNextObjects() {
		List<InvocationObject> next = invocationReader.getLocalNextObjects();
		for (InvocationObject ob : next) {
			SqlSessionSettings settings = ob.getClass().getAnnotation(SqlSessionSettings.class);
			if(settings != null){
				return settings;
			}
		}
		//nothing was found
		return null;
	}

	private SqlSession createSession(SqlSessionSettings settings){
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
