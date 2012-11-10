package com.gf.components.mybatis;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.gf.Action;
import com.gf.InvocationObject;
import com.gf.annotation.Inject;
import com.gf.annotation.Mapping;
import com.gf.annotation.Order;
import com.gf.extra.invocation.reader.InvocationReaderInterceptor;
import com.gf.extra.util.ReflectionsUtil;
import com.gf.service.InterceptorChain;

@Order(Order.SYSTEM_ORDER)
@Mapping(Action.class)
public class InjectMappers extends InvocationReaderInterceptor<Action<?,?>> {
	
	@Inject
	SqlSession session;

	@Override
	public void invoke(Action<?, ?> action, InterceptorChain chain) throws Exception {
		
		List<InvocationObject> nextObjects = invocationReader.getLocalNextObjects();
		for (InvocationObject ob : nextObjects) {
			injectTo(ob, session);
		}
		
		chain.doNext();
		
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
