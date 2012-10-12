package servlet_jdbc.util;

import javax.servlet.ServletConfig;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.nonxa.AtomikosNonXADataSourceBean;
import com.gf.components.tx.TxManager;
import com.gf.log.Log;
import com.gf.log.LogFactory;

/**
 * Manager of connections to a database and transactions
 */
public class AtomikosTxManager implements TxManager {

	private static Log log = LogFactory.getLog(AtomikosTxManager.class);

	private UserTransactionManager utm;
	private AtomikosNonXADataSourceBean ds;

	public AtomikosTxManager(ServletConfig config) {

		try {
			init(config);
		} catch (Exception ex) {
			utm = null;
			throw new RuntimeException("cannot initialize TxManager", ex);
		}

	}

	private void init(ServletConfig config) throws Exception {
		utm = new UserTransactionManager();
		utm.init();
		log.info("inited transaction manager");

		ds = new AtomikosNonXADataSourceBean();
		ds.setUniqueResourceName("TxManager-" + System.currentTimeMillis());

		ds.setUser(config.getInitParameter("db-user"));
		ds.setPassword(config.getInitParameter("db-password"));
		ds.setDriverClassName(config.getInitParameter("db-driver"));
		ds.setUrl(config.getInitParameter("db-url"));
		ds.setPoolSize(Integer.parseInt(config.getInitParameter("db-pool-size")));
		// ds.setTestQuery("Select 1");
		log.info("inited data source");
	}

	@Override
	public void init() throws Exception {
	}

	@Override
	public UserTransaction getUserTransaction() {
		UserTransaction utx = new UserTransactionImp();
		return utx;
	}

	@Override
	public DataSource getDataSource() {
		return ds;
	}

	@Override
	public void destroy() {
		if (utm != null) {
			utm.close();
			log.info("shut down transaction manager");
		}
	}

}
