package com.gf.components.mybatis;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.gf.Action;
import com.gf.Handler;
import com.gf.InvocationObject;
import com.gf.annotation.Inject;
import com.gf.annotation.Order;
import com.gf.extra.invocation.reader.InvocationReaderFilter;
import com.gf.extra.util.ReflectionsUtil;
import com.gf.service.FilterChain;
import com.gf.util.Util;

@Order(Order.SYSTEM_ORDER)
public class SqlSessionInInvoke extends InvocationReaderFilter {
	
	@Inject
	SqlSessionFactory sessionFactory;

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		Handler<?> handler = invocationReader.getHandler();
		SqlSession session = createSession(handler);
		Map<Class<?>, Object> mappersCache = new HashMap<Class<?>, Object>();
		try {
			
			List<InvocationObject> nextObjects = invocationReader.getLocalNextObjects();
			for (InvocationObject ob : nextObjects) {
				injectTo(ob, session, mappersCache);
			}
			
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

	private void injectTo(InvocationObject ob, SqlSession session, 
			Map<Class<?>, Object> mappersCache) throws Exception {
		
		List<Field> fields = ReflectionsUtil.getRequiredFields(ob, Inject.class);
		for (Field field : fields) {
			
			Class<?> injectType = field.getType();
			
			if( ! AbstractMapper.class.isAssignableFrom(injectType)){
				continue;
			}
			
			Object mapper = mappersCache.get(injectType);
			if(mapper == null){
				mapper = session.getMapper(injectType);
				if(mapper == null) {
					throw new IllegalStateException("unknown inject type: "+field.getType()+" for "+ob);
				}
				mappersCache.put(injectType, mapper);
			}
			
			ReflectionsUtil.inject(field, ob, mapper);
		}
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
