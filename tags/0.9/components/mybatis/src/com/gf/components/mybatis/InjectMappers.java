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

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.gf.InvocationObject;
import com.gf.annotation.Inject;
import com.gf.extra.invocation.InvocationObjectInitializer;
import com.gf.extra.util.ReflectionsUtil;

/**
 * Inject mappers initializer.
 * Need {@link SqlSession} object in invocation context 
 * (it can be setted by {@link SqlSessionInInvoke} filter).
 * <br>Example:
 * <pre>
 * //engine
 * import org.apache.ibatis.session.SqlSessionFactory;
 * import com.gf.components.mybatis.SqlSessionInInvoke;
 * import com.gf.components.mybatis.InjectMappers;
 *  
 * SqlSessionFactory sqlSessionFactory = ... 
 *  
 * Engine engine = new Engine();
 * engine.putFilter(SqlSessionInInvoke.class);
 * engine.putInitializer(new InjectMappers());
 * engine.addToContext(sqlSessionFactory);
 * engine.putHandler(SomeHandler.class);
 *  
 *  
 * //mapper
 * import org.apache.ibatis.annotations.Param;
 *  
 * public interface DocMapper  {
 *      
 *     void createDoc(&#064;Param("id")long id, &#064;Param("name")String name);
 *      
 *     int renameDoc(&#064;Param("id")long id, &#064;Param("name")String name);
 *  
 * }
 *  
 *  
 * //handler
 * import java.sql.Connection;
 * import org.apache.ibatis.session.SqlSession;
 *  
 * &#064;Mapping(SomeAction.class)
 * public class SomeHandler extends Handler&lt;SomeAction&gt;{
 *      
 *     &#064;Inject
 *     DocMapper docMapper;
 *  
 *     public void invoke(SomeAction action) throws Exception {
 *         ...
 *     }
 *  
 * }
 * </pre>
 *
 * @author Evgeny Dolganov
 *
 */
public class InjectMappers extends InvocationObjectInitializer {


	@Override
	public void beforeObjectInited(InvocationObject obj, Collection<Object> context) throws Exception {
		
		SqlSession session = findByType(context, SqlSession.class);
		if(session == null){
			return;
		}
		
		injectTo(obj, session);
		
	}
	
	private void injectTo(InvocationObject ob, SqlSession session) throws Exception {
		
		List<Field> fields = ReflectionsUtil.getRequiredFields(ob, Inject.class);
		for (Field field : fields) {
			
			Class<?> injectType = field.getType();
			
			if( ! AbstractMapper.class.isAssignableFrom(injectType)){
				continue;
			}
			
			Object mapper = session.getMapper(injectType);
			if(mapper == null) {
				throw new IllegalStateException("unknown inject type: "+field.getType()+" for "+ob);
			}
			
			ReflectionsUtil.inject(field, ob, mapper);
		}
	}

}
