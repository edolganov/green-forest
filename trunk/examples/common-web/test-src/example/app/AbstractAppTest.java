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
	
	protected IApp app;
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
