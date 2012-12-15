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
package example.app;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.junit.Before;

import com.gf.core.Engine;
import com.gf.log.Log;
import com.gf.log.LogFactory;
import com.gf.util.ReflectionsUtil;
import com.gf.util.junit.AssertExt;

public abstract class AbstractAppTest extends AssertExt {
	
	private static ThreadLocal<AppProvider> providers = new ThreadLocal<AppProvider>();
	
	public static void setTestContext(AppProvider provider){
		if(provider != null){
			providers.set(provider);
		} else {
			providers.remove();
		}
	}
	
	private static AppProvider getProvider(){
		return providers.get();
	}
	
	protected Log log = LogFactory.getLog(getClass());
	
	protected App app;
	protected Engine appEngine;
	protected DataSource ds;
	
	@Before
	public void init() throws Exception{
		AppProvider provider = getProvider();
		if(provider == null){
			log.warn("SKIP TEST "+getClass().getSimpleName()+": no context");
			org.junit.Assume.assumeTrue(false);
		}
		
		AppContext context = provider.createContext();
		this.app = context.app;
		this.appEngine = (Engine) ReflectionsUtil.getField(app, "actionService");
		this.ds = context.ds;
	}
	
	public void printDocTable() throws Exception {
		
		Connection connection = ds.getConnection();
		ResultSet rs = connection.createStatement().executeQuery("select * from doc");
		System.out.println("id \t name");
		while(rs.next()){
			String id = rs.getString(1);
			String name = rs.getString(2);
			System.out.println(id+"\t"+name);
		}
		rs.next();
		
		rs.close();
		connection.close();
	}
	
	
	public String getDocName(int id) throws Exception {
		
		Connection connection = ds.getConnection();
		ResultSet rs = connection.createStatement().executeQuery("select name from doc where id="+id);
		rs.next();
		String out = rs.getString(1);
		rs.close();
		connection.close();
		
		return out;
	}

}
