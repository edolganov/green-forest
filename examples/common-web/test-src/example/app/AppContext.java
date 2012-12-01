package example.app;

import javax.sql.DataSource;


public class AppContext {
	
	public IApp app;
	public DataSource ds;
	
	public AppContext(IApp app, DataSource ds) {
		super();
		this.app = app;
		this.ds = ds;
	}

}
