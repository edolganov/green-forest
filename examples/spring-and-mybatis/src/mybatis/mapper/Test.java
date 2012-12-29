package mybatis.mapper;

import org.apache.ibatis.session.SqlSessionFactory;

import com.gf.components.mybatis.SqlSessionInInvoke;
import com.gf.core.Engine;

public class Test {
	
	public static void main(String[] args) {
		
		SqlSessionFactory sqlSessionFactory = getFactory(); 
		
		Engine engine = new Engine();
		engine.putFilter(SqlSessionInInvoke.class);
		engine.addToContext(sqlSessionFactory);
		engine.putHandler(SomeHandler.class);
		
		
	}

	private static SqlSessionFactory getFactory() {
		// TODO Auto-generated method stub
		return null;
	}

}
