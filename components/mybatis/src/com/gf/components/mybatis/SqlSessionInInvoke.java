/*
 * Copyright 2012 Evgeny Dolganov (evgenij.dolganov@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gf.components.mybatis;

import java.sql.Connection;
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

/**
 * Filter for creating {@link SqlSession} and adding it into the handler's context.
 * Also the filter adds {@link Connection} object into the handler's context.
 * For work the filter needs a {@link SqlSessionFactory} object in context.
 * <br>Example:
 * <pre>
 * //engine
 * SqlSessionFactory sqlSessionFactory = ...
 * Engine engine = new Engine();
 * engine.addToContext(sqlSessionFactory);
 * engine.putFilter(SqlSessionInInvoke.class);
 * engine.putHandler(SomeHandler.class);
 * 
 * //handler
 * import java.sql.Connection;
 * import org.apache.ibatis.session.SqlSession;
 * 
 * &#064;Mapping(SomeAction.class)
 * public class SomeHandler extends Handler&lt;SomeAction&gt;{
 *   
 *   &#064;Inject
 *   Connection connection;
 *   
 *   &#064;Inject
 *   SqlSession session;
 * 
 *   public void invoke(SomeAction action) throws Exception {
 *   	//...
 *   }
 * 
 * }
 * </pre>
 * 
 * <p>Also you can setup SqlSession's {@link ExecutorType} in a handler by {@link SqlSessionSettings} annotaiton:
 * <pre>
 * &#064;SqlSessionSettings(execType=ExecutorType.BATCH)
 * &#064;Mapping(SomeActon.class)
 * public class SomeHandler extends Handler&lt;SomeActon&gt;{
 * 
 *   public void invoke(SomeActon action) throws Exception {
 *      ...
 *   }
 * 
 * }
 * </pre>
 * @author Evgeny Dolganov
 *
 */
@Order(Order.SYSTEM_ORDER)
public class SqlSessionInInvoke extends InvocationReaderFilter {
	
	@Inject
	SqlSessionFactory sessionFactory;

	@Override
	public void invoke(Action<?, ?> action, FilterChain chain) throws Exception {
		
		SqlSessionSettings settings = findSettingsInNextObjects();
		SqlSession session = createSession(settings);
		try {
			
			Connection connection = session.getConnection();
			
			addToInvocationContext(session);
			addToInvocationContext(connection);
			chain.doNext();
			
			session.flushStatements();
			session.commit();
			
		}catch (Exception e) {
			session.rollback();
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
