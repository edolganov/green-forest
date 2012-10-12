package servlet_jdbc.util;

import java.util.Properties;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
import com.gf.log.Log;
import com.gf.log.LogFactory;

public class AtomicosTxManager {
	
	private static Log log = LogFactory.getLog(AtomicosTxManager.class);
	
	private UserTransactionManager utm;
    private AtomikosNonXADataSourceBean ds;
    
    /*
db.data-source.driverClassName=org.h2.Driver
db.data-source.url=jdbc:h2:../data/mind-storage-db
db.data-source.user=sa
db.data-source.password=
db.data-source.poolSize=5
     *
     */
    
    public AtomicosTxManager(Properties props) {
    	try {
            utm = new UserTransactionManager();
            utm.init();
            log.info("initialized transaction manager");
        } catch (Exception ex) {
            utm = null;
            throw new RuntimeException("cannot initialize UserTransactionManager", ex);
        }
                
                
        ds = new AtomikosNonXADataSourceBean();
        ds.setUniqueResourceName("AtomicosTxManager-"+System.currentTimeMillis());
        ds.setUser(props.getProperty("user"));
        ds.setPassword (props.getProperty("password"));
        ds.setDriverClassName (props.getProperty("driver"));
        ds.setUrl(props.getProperty("url"));
        //ds.setTestQuery("Select 1");
        if(props.containsKey("pool-size")){
        	ds.setPoolSize(Integer.parseInt(props.getProperty("pool-size")));
        }
        
	}
    
    public UserTransaction getUserTransaction() {
        UserTransaction utx = new UserTransactionImp();
        return utx;
    }

    public DataSource getDataSource() {
        return ds;
    }

    public void destroy() {
	    if (utm != null) {
	        utm.close();
	        log.info("shut down transaction manager");
	    }
    }

}
